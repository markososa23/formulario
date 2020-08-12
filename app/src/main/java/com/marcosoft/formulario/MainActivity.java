package com.marcosoft.formulario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txtCodigo,txtDescripcion,txtPrecio;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCodigo=(EditText)findViewById(R.id.txtCodigo);
        txtDescripcion=(EditText)findViewById(R.id.txtDescripcion);
        txtPrecio=(EditText)findViewById(R.id.txtPrecio);
        btnGuardar=(Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
ejecutarServicio("http://192.168.0.15/kiosco/insertarOptimo.php");
                                          }
                                      }
        );
    }
      private void ejecutarServicio(String URL){
          StringRequest stringRequest=new StringRequest(Method.POST, URL, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  Toast.makeText(getApplicationContext(), "Operacion exitosa"+response, Toast.LENGTH_SHORT).show();
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
              }
          }){
              @Override
              protected Map<String, String> getParams() {
                  Map<String,String> parametros= new HashMap<>();
                  parametros.put("codProducto",txtCodigo.getText().toString());
                  parametros.put("descripcion",txtDescripcion.getText().toString());
                  parametros.put("precio",txtPrecio.getText().toString());
                  return  parametros;

              }
      /*     @Override
           public Map getHeaders() {
               HashMap headers = new HashMap();
               headers.put("Content-Type", "text/plain; charset=utf-8");
               headers.put("codProducto",txtCodigo.getText().toString());
               headers.put("descripcion",txtDescripcion.getText().toString());
               headers.put("precio",txtPrecio.getText().toString());
               return headers;
           }*/
      };
          RequestQueue requestQueue = Volley.newRequestQueue(this);
          requestQueue.add(stringRequest);
    }
}