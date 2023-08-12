package com.example.repairbrain20;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HabitsWindow extends Fragment {

    ListView list_view;
    ImageView no_results = null;
    TextView percent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_habits,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout main = view.findViewById(R.id.main);

        list_view = view.findViewById(R.id.list);
        no_results = view.findViewById(R.id.no_results);
        percent = view.findViewById(R.id.percent);

        list_view.setVisibility(View.GONE);
        no_results.setVisibility(View.VISIBLE);

        Glide.with(no_results)
                .load(R.drawable.loading_pink_list)
                .into(no_results);

        /*
            add = view.findViewById(R.id.add);
            percent.setText(String.valueOf(HabitsAndAccuracy.last_accuracy_percent));
         */

        DatabaseReference reference = User.getReference();

        if(reference!=null)
        {
            reference
                    .child("replace_habits")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {

                            Map<String,ReplaceHabits> habits = task.getResult().getValue(new GenericTypeIndicator<Map<String, ReplaceHabits>>() {
                                @NonNull
                                @Override
                                public String toString() {
                                    return super.toString();
                                }
                            });

                            // Log.e("uruttu_complete",habits.toString());

                            if(habits!=null)
                            {
                                list_view.setVisibility(View.VISIBLE);
                                no_results.setVisibility(View.GONE);
                                list_view.setAdapter(new HabitsAdapter(getActivity(),habits));
                            }
                            else
                            {
                                list_view.setVisibility(View.GONE);
                                no_results.setVisibility(View.VISIBLE);
                                no_results.setImageResource(R.drawable.noresultfound);
                            }
                        }
                    });
        }
    }

    public void addHabit()
    {
        List<String> show_on = new ArrayList<String>();

        View view = getActivity().getLayoutInflater().inflate(R.layout.habits_add,null);

        EditText habit = view.findViewById(R.id.habit);

        CheckBox sun = view.findViewById(R.id.sun);
        CheckBox mon = view.findViewById(R.id.mon);
        CheckBox tue = view.findViewById(R.id.tue);
        CheckBox wed = view.findViewById(R.id.wed);
        CheckBox thur = view.findViewById(R.id.thur);
        CheckBox fri = view.findViewById(R.id.fri);
        CheckBox sat = view.findViewById(R.id.sat);
        CheckBox all = view.findViewById(R.id.all);

        CompoundButton.OnCheckedChangeListener checked_listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId())
                {
                    case R.id.all:
                        if(b)
                        {
                            show_on.clear();
                            sun.setChecked(true);
                            mon.setChecked(true);
                            tue.setChecked(true);
                            wed.setChecked(true);
                            thur.setChecked(true);
                            fri.setChecked(true);
                            sat.setChecked(true);
                        }
                        break;


                    case R.id.sun:
                        if(b)
                        {
                            show_on.add("Sun");
                        }
                        else
                        {
                            show_on.remove("Sun");
                        }
                        break;


                    case R.id.mon:
                        if(b)
                        {
                            show_on.add("Mon");
                        }
                        else
                        {
                            show_on.remove("Mon");
                        }
                        break;


                    case R.id.tue:
                        if(b)
                        {
                            show_on.add("Tue");
                        }
                        else
                        {
                            show_on.remove("Tue");
                        }
                        break;


                    case R.id.wed:
                        if(b)
                        {
                            show_on.add("Wed");
                        }
                        else
                        {
                            show_on.remove("Wed");
                        }
                        break;


                    case R.id.thur:
                        if(b)
                        {
                            show_on.add("Thur");
                        }
                        else
                        {
                            show_on.remove("Thur");
                        }
                        break;


                    case R.id.fri:
                        if(b)
                        {
                            show_on.add("Fri");
                        }
                        else
                        {
                            show_on.remove("Fri");
                        }
                        break;


                    case R.id.sat:
                        if(b)
                        {
                            show_on.add("Sat");
                        }
                        else
                        {
                            show_on.remove("Sat");
                        }
                        break;
                }
            }
        };

        all.setOnCheckedChangeListener(checked_listener);
        sun.setOnCheckedChangeListener(checked_listener);
        mon.setOnCheckedChangeListener(checked_listener);
        tue.setOnCheckedChangeListener(checked_listener);
        wed.setOnCheckedChangeListener(checked_listener);
        thur.setOnCheckedChangeListener(checked_listener);
        fri.setOnCheckedChangeListener(checked_listener);
        sat.setOnCheckedChangeListener(checked_listener);

        new AlertDialog.Builder(getActivity())
                .setView(view)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String habit_ = habit.getText().toString().trim();

                        if(habit_.trim().equals(""))
                        {
                            Toast.makeText(getContext(),"Habit cannot be empty",Toast.LENGTH_LONG).show();
                            return;
                        }

                        if(show_on.size()==0)
                        {
                            Toast.makeText(getContext(),"Days not selected",Toast.LENGTH_LONG).show();
                            return;
                        }

                        Snackbar connect =  Snackbar.make(getView(),"Connecting",Snackbar.LENGTH_INDEFINITE);
                        connect.show();

                        ReplaceHabits habits = new ReplaceHabits(show_on);

                        Map<String,ReplaceHabits> habits_map;
                        habits_map = HabitsAdapter.habits_copy;

                        if(habits_map!=null)
                        {
                            habits_map.put(habit_,habits);
                        }
                        else
                        {
                            habits_map = new HashMap<>();
                            habits_map.put(habit_,habits);
                        }

                        HabitsAdapter adapter = new HabitsAdapter(getActivity(),habits_map);

                        User.getReference()
                                .child("replace_habits")
                                .child(habit_)
                                .setValue(habits)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        connect.dismiss();
                                        Toast.makeText(getActivity(),"Habit Added",Toast.LENGTH_SHORT).show();
                                        percent.setText("0%");
                                        no_results.setVisibility(View.GONE);
                                        list_view.setVisibility(View.VISIBLE);
                                        list_view.setAdapter(adapter);
                                    }
                                });
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //item.setTitle("ADD");
                    }
                })
                .create().show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.habits_frament_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.add:
                addHabit();
                break;

            case R.id.remove:
                Map<String,ReplaceHabits> habits_copy =  HabitsAdapter.habits_copy;
                if(habits_copy.size()==0)
                {
                    Toast.makeText(getActivity(),"Habit List is Empty",Toast.LENGTH_LONG).show();
                }
                HabitsAdapter remove_adapter = new HabitsAdapter(getActivity(),habits_copy,true);
                list_view.setAdapter(remove_adapter);
                break;

            case R.id.reset:
                User.getReference()
                        .child("replace_habits")
                        .removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                list_view.setAdapter(new HabitsAdapter(getActivity(),new HashMap<>()));
                                Toast.makeText(getContext(),"Successfully Resetted",Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}