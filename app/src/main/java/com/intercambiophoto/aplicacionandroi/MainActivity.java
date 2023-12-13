package com.intercambiophoto.aplicacionandroi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    private String clienteId="";
    //Conexión al servidor
    //mqtt://appandroi:Rp7wVZaXNfy3MQHn@appandroi.cloud.shiftr.io
    private static String mqttHost = "tcp://appandroi.cloud.shiftr.io:1883";
    private static String mqttUser = "appandroi";
    private static String mqttPass = "Rp7wVZaXNfy3MQHn";
    private MqttAndroidClient cliente;
    private MqttConnectOptions opciones;
    private static String topic = "Usuario activo";
    private static String topicMsgOn = "Iniciar Sesion";
    private static String topicMsgOff = "cerrar sesion";


    private boolean permisoPublicar;


    private EditText correo;
    private EditText contrasena;

    private FirebaseAuth mAuth;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);

        mAuth = FirebaseAuth.getInstance();

    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void iniciarsesion(View view){

        mAuth.signInWithEmailAndPassword(correo.getText().toString(),contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "Authentication correcta.", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), inicioactivity.class );
                            Button btnOn = findViewById(R.id.btniniciarsesion);
                            btnOn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    enviarMensaje(topic, topicMsgOn);
                                }
                            });

                            getNombreCliente();
                            connectBroker();
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }
    private void checkConnection(){
        if(this.cliente.isConnected()){
            this.permisoPublicar = true;
        }
        else{
            this.permisoPublicar = false;
            connectBroker();
        }
    }
    private void enviarMensaje(String topic, String msg){
        checkConnection();
        try{
            int qos = 0;
            this.cliente.publish(topic, msg.getBytes(), qos, false);
            Toast.makeText(getBaseContext(), topic + ":" + msg, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void cerrarsesion(){
        Button btnOff = findViewById(R.id.btncerrarsesion);
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMensaje(topic, topicMsgOff);
            }
        });

    }
    private void connectBroker(){
        this.cliente = new MqttAndroidClient(this.getApplicationContext(), mqttHost, this.clienteId);
        this.opciones = new MqttConnectOptions();
        this.opciones.setUserName(mqttUser);
        this.opciones.setPassword(mqttPass.toCharArray());
        try{
            IMqttToken token = this.cliente.connect(opciones);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this, "Conectado", Toast.LENGTH_SHORT).show();

                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this, "Falló conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (MqttException e){
            e.printStackTrace();
        }
    }
    private void suscribirseTopic(){
        try {
            this.cliente.subscribe(topic, 0);
        }
        catch (MqttSecurityException e){
            e.printStackTrace();
        }
        catch (MqttException e){
            e.printStackTrace();
        }
        this.cliente.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Toast.makeText(getBaseContext(), "Se desconectó el servidor", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                if(topic.matches(topic)){
                    String msg = new String(message.getPayload());
                    if(msg.matches(topicMsgOn)){

                    }
                    else if(msg.matches(topicMsgOff)){

                    }
                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
    }
    private void getNombreCliente(){
        String manufacturer = Build.MANUFACTURER;
        String modelName = Build.MODEL;
        this.clienteId = manufacturer + " " + modelName;
        TextView idConexion = findViewById(R.id.idConexion);
        idConexion.setText(this.clienteId);
    }



    public void Registrarse(View view) {
        Intent i = new Intent(this, registro.class);
        startActivity(i);
    }

   /* public void iniciarsesion(View view) {
        Intent i = new Intent(this, inicioactivity.class);
        startActivity(i);
        }*/

    }
