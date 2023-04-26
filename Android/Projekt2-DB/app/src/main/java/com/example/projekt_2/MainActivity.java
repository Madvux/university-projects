package com.example.projekt_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_PHONE_REQUEST = 1;
    public static final int EDIT_PHONE_REQUEST = 2;
    private PhoneViewModel phoneViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_db);


        FloatingActionButton buttonAddPhone = findViewById(R.id.button_add_phone);
        buttonAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditPhoneActivity.class);
                startActivityForResult(intent, ADD_PHONE_REQUEST);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        PhoneAdapter adapter = new PhoneAdapter();
        recyclerView.setAdapter(adapter);

        phoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);
        phoneViewModel.getAllPhones().observe(this, new Observer<List<Phone>>() {
            @Override
            public void onChanged(List<Phone> phones) {
                adapter.setPhones(phones);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                phoneViewModel.delete(adapter.getPhoneAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, R.string.deleted, Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new PhoneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Phone phone) {
                Intent intent = new Intent(MainActivity.this, AddEditPhoneActivity.class);
                intent.putExtra(AddEditPhoneActivity.EXTRA_ID, phone.getId());
                intent.putExtra(AddEditPhoneActivity.EXTRA_PRODUCENT, phone.getProducent());
                intent.putExtra(AddEditPhoneActivity.EXTRA_MODEL, phone.getModel());
                intent.putExtra(AddEditPhoneActivity.EXTRA_ANDROID_VERSION, phone.getAndroid_version());
                intent.putExtra(AddEditPhoneActivity.EXTRA_WEBPAGE, phone.getWebpage());
                startActivityForResult(intent, EDIT_PHONE_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PHONE_REQUEST && resultCode == RESULT_OK) {
            String producent = data.getStringExtra(AddEditPhoneActivity.EXTRA_PRODUCENT);
            String model = data.getStringExtra(AddEditPhoneActivity.EXTRA_MODEL);
            int android_version = Integer.parseInt(data.getStringExtra(AddEditPhoneActivity.EXTRA_ANDROID_VERSION));
            String webpage = data.getStringExtra(AddEditPhoneActivity.EXTRA_WEBPAGE);

            Phone phone = new Phone(producent, model, android_version, webpage);
            phoneViewModel.insert(phone);

            Toast.makeText(this, R.string.info_save, Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PHONE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditPhoneActivity.EXTRA_ID, -1);
            if (id == -1){
                Toast.makeText(this, R.string.action_failed, Toast.LENGTH_SHORT).show();
                return;
            }

            String producent = data.getStringExtra(AddEditPhoneActivity.EXTRA_PRODUCENT);
            String model = data.getStringExtra(AddEditPhoneActivity.EXTRA_MODEL);
            int android_version = Integer.parseInt(data.getStringExtra(AddEditPhoneActivity.EXTRA_ANDROID_VERSION));
            String webpage = data.getStringExtra(AddEditPhoneActivity.EXTRA_WEBPAGE);

            Phone phone = new Phone(producent, model, android_version, webpage);
            phone.setId(id);
            phoneViewModel.update(phone);

            Toast.makeText(this, R.string.info_update, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_phones:
                phoneViewModel.deleteAllPhones();
                Toast.makeText(this, "All phones deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}