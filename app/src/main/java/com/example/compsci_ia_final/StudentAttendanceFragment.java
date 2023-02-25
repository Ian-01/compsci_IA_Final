package com.example.compsci_ia_final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceFragment extends Fragment {

    private ArrayList<StudentAttendance> mStudentAttendanceList = new ArrayList<>();

    Button btnSaveAttendance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_student_attendance, container, false);

        // Find and assign the RecyclerView.
        RecyclerView recyclerView = view.findViewById(R.id.rv_student_attendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Create and set the adapter
        StudentAttendanceAdapter adapter = new StudentAttendanceAdapter(mStudentAttendanceList);
        recyclerView.setAdapter(adapter);

        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getStudentTrackRecordList().observe(getViewLifecycleOwner(), new Observer<List<StudentTrackRecord>>() {
            @Override
            public void onChanged(List<StudentTrackRecord> studentTrackRecords) {
                ArrayList<StudentAttendance> temp = new ArrayList<>();
                for (StudentTrackRecord studentTrackRecord : studentTrackRecords) {
                    temp.add(new StudentAttendance(studentTrackRecord.getName()));
                }
                adapter.updateList(temp);
            }


        });

        btnSaveAttendance = view.findViewById(R.id.btn_save_attendance);

        btnSaveAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change code below
                ArrayList<StudentAttendance> updatedStudentAttendanceList = new ArrayList<>();

                for (int i = 0; i < adapter.getItemCount(); i++) {
                    StudentAttendance updatedStudentAttendance = adapter.mStudentAttendanceList.get(i);
                    updatedStudentAttendanceList.add(updatedStudentAttendance);
                }



                viewModel.updateStudentTrackRecord(updatedStudentAttendanceList);

            }
        });
        return view;
    }


    private class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.StudentAttendanceViewHolder> {

        private ArrayList<StudentAttendance> mStudentAttendanceList;

        public StudentAttendanceAdapter(ArrayList<StudentAttendance> studentAttendanceList) {
            mStudentAttendanceList = studentAttendanceList;
        }

        @Override
        public StudentAttendanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.rv_item_student_attendance, parent, false);
            return new StudentAttendanceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(StudentAttendanceViewHolder holder, int position) {


            StudentAttendance studentAttendance = mStudentAttendanceList.get(position);
            holder.mNameTextView.setText(studentAttendance.getName());

            holder.mPresentToggleButton.setChecked(studentAttendance.isPresent());
            holder.mAbsentToggleButton.setChecked(studentAttendance.isAbsent());
            holder.mLateToggleButton.setChecked(studentAttendance.isLate());
        }

        @Override
        public int getItemCount() {
            return mStudentAttendanceList.size();
        }

        //update list
        public void updateList(ArrayList<StudentAttendance> newList) {
            this.mStudentAttendanceList = newList;
            notifyDataSetChanged();
        }

        private class StudentAttendanceViewHolder extends RecyclerView.ViewHolder {
            private TextView mNameTextView;
            private ToggleButton mPresentToggleButton;
            private ToggleButton mAbsentToggleButton;
            private ToggleButton mLateToggleButton;

            public StudentAttendanceViewHolder(View itemView) {
                super(itemView);
                mNameTextView = itemView.findViewById(R.id.text_student_name);
                mPresentToggleButton = itemView.findViewById(R.id.toggle_present);
                mAbsentToggleButton = itemView.findViewById(R.id.toggle_absent);
                mLateToggleButton = itemView.findViewById(R.id.toggle_late);


                mPresentToggleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean present = mPresentToggleButton.isChecked();
                        StudentAttendance studentAttendance = mStudentAttendanceList.get(getAdapterPosition());

                        if (!present) {
                            studentAttendance.setUndecided();
                        } else {
                            studentAttendance.setPresent();
                        }
                        notifyDataSetChanged();
                        Log.d("StudentAttendanceFragment", studentAttendance.getName() + ": present = " + studentAttendance.isPresent() + ", absent = " + studentAttendance.isAbsent() + ", late = " + studentAttendance.isLate() + ", undecided = " + studentAttendance.isUndecided());

                        mLateToggleButton.setChecked(false);
                        mAbsentToggleButton.setChecked(false);
                    }
                });

                mAbsentToggleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean absent = mAbsentToggleButton.isChecked();
                        StudentAttendance studentAttendance = mStudentAttendanceList.get(getAdapterPosition());

                        if (!absent) {
                            studentAttendance.setUndecided();
                        } else {
                            studentAttendance.setAbsent();
                        }
                        notifyDataSetChanged();
                        Log.d("StudentAttendanceFragment", studentAttendance.getName() + ": present = " + studentAttendance.isPresent() + ", absent = " + studentAttendance.isAbsent() + ", late = " + studentAttendance.isLate() + ", undecided = " + studentAttendance.isUndecided());

                        mPresentToggleButton.setChecked(false);
                        mLateToggleButton.setChecked(false);
                    }
                });

                mLateToggleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean late = mLateToggleButton.isChecked();
                        StudentAttendance studentAttendance = mStudentAttendanceList.get(getAdapterPosition());

                        if (!late) {
                            studentAttendance.setUndecided();
                        } else {
                            studentAttendance.setLate();
                        }
                        notifyDataSetChanged();
                        Log.d("StudentAttendanceFragment", studentAttendance.getName() + ": present = " + studentAttendance.isPresent() + ", absent = " + studentAttendance.isAbsent() + ", late = " + studentAttendance.isLate() + ", undecided = " + studentAttendance.isUndecided());

                        mPresentToggleButton.setChecked(false);
                        mAbsentToggleButton.setChecked(false);
                    }
                });
            }

            public void bind(StudentAttendance studentAttendance) {
                mNameTextView.setText(studentAttendance.getName());


            }
            /*
            The StudentAttendanceViewHolder class is an inner class that extends RecyclerView.ViewHolder. It represents a single item view in the RecyclerView.

The class has a constructor that takes a View object, which is typically the inflated layout for the item view. It also has a bind method that takes a StudentAttendance object and sets the name of the student in the corresponding TextView.

The class has three ToggleButton fields mPresentToggleButton, mAbsentToggleButton, and mLateToggleButton. These toggle buttons are used to mark attendance for each student.

The class also has three OnClickListener objects, one for each toggle button. These listeners are set up in the constructor and define what happens when a toggle button is clicked. When a toggle button is clicked, the corresponding StudentAttendance object is updated with the attendance status, the adapter is notified of the data change, and the state of the other toggle buttons is reset.

Each listener logs the updated status of the student's attendance with the Log.d method.
            * */

        }
    }
}