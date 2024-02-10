package com.sewista.fragment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.sewista.R;

public class MapsFragment extends Fragment {

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    private final OnMapReadyCallback callback = googleMap -> {
        LatLng pmf = new LatLng(45.2456135, 19.8531105);
        MarkerOptions markerInPmf = new MarkerOptions().position(pmf).title("PMF");
        markerInPmf.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerInPmf);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pmf));

//        LatLng current = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//        MarkerOptions markerInCurrent = new MarkerOptions().position(current).title("Current location");
//        markerInCurrent.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
//        googleMap.addMarker(markerInCurrent);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(current));

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        getLocation();
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;

                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null)
                    mapFragment.getMapAsync(callback);
            }
        });
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    getLocation();
                } else {
                    Toast.makeText(requireContext(), "Location permission is denied!", Toast.LENGTH_SHORT).show();
                }
            }
    );
}