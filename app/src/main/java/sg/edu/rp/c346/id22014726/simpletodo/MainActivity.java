package sg.edu.rp.c346.id22014726.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editToDo;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvToDo;
    Spinner spinnerChoice;
    ArrayList<String> alToDo;
    ArrayAdapter aaToDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("ArrayList");
        alToDo = new ArrayList<>();
        ArrayList<String> taskEntries = new ArrayList<>();

        editToDo = findViewById(R.id.editToDo);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        lvToDo = findViewById(R.id.lvTasks);
        spinnerChoice = findViewById(R.id.spinner);

        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);

        lvToDo.setAdapter(aaToDo);

        spinnerChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = parent.getItemAtPosition(position).toString();
                if (select.equals("Add a new task")) {
                    editToDo.setHint("Type in new task here");
                    btnAdd.setEnabled(true);
                    btnDelete.setEnabled(false);
                    aaToDo = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, taskEntries);
                    lvToDo.setAdapter(aaToDo);
                } else if (select.equals("Remove a task")) {
                    editToDo.setHint("Type in the index of the task to be remove");
                    btnAdd.setEnabled(false);
                    btnDelete.setEnabled(true);
                    aaToDo = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alToDo);
                    lvToDo.setAdapter(aaToDo);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTasks = editToDo.getText().toString();
                if (!newTasks.isEmpty()) {
                    taskEntries.add(newTasks);
                    alToDo.add(newTasks);
                    aaToDo.notifyDataSetChanged();
                    editToDo.setText(null);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (alToDo.isEmpty()) {
                Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
            }
                int index = Integer.parseInt(editToDo.getText().toString());
                if (index >= 0 && index < alToDo.size()) {
                    alToDo.remove(index);
                    aaToDo.notifyDataSetChanged();
                    editToDo.setText(null);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
                editToDo.setText(null);
                }
            });
        }
    }