package hr.foi.air.g2.vjezbe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Context pc = this;
        
        Button btnOpenMap = (Button) findViewById(R.id.btnOpenMap);
        
        btnOpenMap.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(pc, MapActivity.class);
				startActivity(i);
			}
		});
        
    	Button btnOpenDb = (Button) findViewById(R.id.btnOpenDb);        
    	btnOpenDb.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(pc, DbActivity.class);
				startActivity(i);
			}
		});
        
    }    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
