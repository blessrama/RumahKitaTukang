package blessrama.pkm.rumahkitatukang.fragment;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import blessrama.pkm.rumahkitatukang.R;
import blessrama.pkm.rumahkitatukang.activity.LoginActivity;
import blessrama.pkm.rumahkitatukang.model.User;

public class ProfileFragment extends Fragment {

    private TextView txtUserName, txtUserEmailAddress, txtPhoneNumber, txtContractInProgress, txtContractCompleted, txtUserHomeAddress, txtUserDescription;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private ProgressDialog progressDialog;

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Tunggu dulu...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        txtUserName = view.findViewById(R.id.txtUserName);
        txtUserEmailAddress = view.findViewById(R.id.txtUserEmailAddress);
        txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
        txtContractInProgress = view.findViewById(R.id.txtContractInProgress);
        txtContractCompleted = view.findViewById(R.id.txtContractCompleted);
        txtUserHomeAddress = view.findViewById(R.id.txtUserHomeAddress);
        txtUserDescription = view.findViewById(R.id.txtUserDescription);

        databaseReference.child("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                txtUserName.setText(user.getName());
                txtUserEmailAddress.setText(user.getEmailAddress());
                txtPhoneNumber.setText(user.getPhoneNumber());
                txtContractInProgress.setText(String.valueOf(user.getContractInProgress()));
                txtContractCompleted.setText(String.valueOf(user.getContractCompleted()));
                txtUserHomeAddress.setText(user.getHomeAddress());
                txtUserDescription.setText(user.getDescription());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
