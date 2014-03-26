package de.work.gps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener{

	LocationManager manager;
	boolean networkEnabled;
	Location oldLocation;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        // 3000 = 300 Millisekunden ==> Location updaten
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
		
		networkEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if(!networkEnabled){
			startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		}
		
    }

	@Override
	public void onLocationChanged(Location location) {
		if(location != oldLocation){
		  Toast.makeText(this, "Location hat sich geändert", Toast.LENGTH_SHORT).show();
		  Toast.makeText(this, "Latitude: " + location.getLatitude(), Toast.LENGTH_SHORT).show();
		  Toast.makeText(this, "Longtitude: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onProviderDisabled(String provider) {
		  Toast.makeText(this, "GPS deaktiviert", Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onProviderEnabled(String provider) {
		  Toast.makeText(this, "GPS aktiviert", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

}

