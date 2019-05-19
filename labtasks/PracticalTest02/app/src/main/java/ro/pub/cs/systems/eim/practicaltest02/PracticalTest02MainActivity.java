package ro.pub.cs.systems.eim.practicaltest02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest02MainActivity extends AppCompatActivity {

    EditText port_server, address_client, port_client, city;
    TextView weatherForecastTextView;
    Button connect, get_weather;
    Spinner operation;

    ServerThread serverThread;
    ClientThread clientThread;

    private ConnectButtonOnClickListener connect_button = new ConnectButtonOnClickListener();
    private class ConnectButtonOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            String serverPort = port_server.getText().toString();
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() == null) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();
        }

    }

    private GetWeatherListener get_button = new GetWeatherListener();
    private class GetWeatherListener implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            String clientAddress = address_client.getText().toString();
            String clientPort = port_client.getText().toString();
            if (clientAddress == null || clientAddress.isEmpty()
                    || clientPort == null || clientPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Client connection parameters should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }
            String city_name = city.getText().toString();
            String informationType = operation.getSelectedItem().toString();
            if (city_name == null || city_name.isEmpty()
                    || informationType == null || informationType.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Parameters from client (city / information type) should be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            weatherForecastTextView.setText(Constants.EMPTY_STRING);

            clientThread = new ClientThread(
                    clientAddress, Integer.parseInt(clientPort), city_name, informationType, weatherForecastTextView
            );
            clientThread.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        port_server = (EditText)findViewById(R.id.portServer);
        address_client = (EditText)findViewById(R.id.addressClient);
        port_client = (EditText)findViewById(R.id.portClient);
        city = (EditText)findViewById(R.id.city);
        connect = (Button)findViewById(R.id.connect);
        get_weather = (Button)findViewById(R.id.get_weather);
        operation = (Spinner)findViewById(R.id.spinner);

        weatherForecastTextView = (TextView)findViewById(R.id.weather_text_view);

        get_weather.setOnClickListener(get_button);
        connect.setOnClickListener(connect_button);
    }
}
