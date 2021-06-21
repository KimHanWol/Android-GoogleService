package kr.ac.jbnu.se.mobile.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fm_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();

        //마커 추가
        markerOptions.position(SEOUL).title("서울").snippet("한국의 수도");
        googleMap.addMarker(markerOptions);

        //범위 원 추가
        CircleOptions circleOptions = new CircleOptions().center(SEOUL)
                .radius(300) //반지름 단위 : m
                .strokeWidth(0f) //선너비 0f : 선 없음
                .fillColor(Color.parseColor("#880000ff"));
        googleMap.addCircle(circleOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
    }
}