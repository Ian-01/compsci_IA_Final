package com.example.compsci_ia_final;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class StudentTrackRecordFragment extends Fragment {
    private static final int ADD_MEMBER_REQUEST_CODE = 1;

    private RecyclerView mRecyclerView;
    private List<StudentTrackRecord> mStudentTrackRecords = new ArrayList<>();

    private Button btnAddMember;

    public StudentTrackRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_track_record, container, false);

        btnAddMember = view.findViewById(R.id.btn_add_member);

        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditMemberActivity.class);
                startActivityForResult(intent, ADD_MEMBER_REQUEST_CODE);
            }
        });

        // Find the RecyclerView
        mRecyclerView = view.findViewById(R.id.rv_student_track_record);

        // Set the layout manager and adapter for the RecyclerView
        StudentTrackRecordAdapter adapter = new StudentTrackRecordAdapter(mStudentTrackRecords);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        // Populate the list of student track records



        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getStudentTrackRecordList().observe(getViewLifecycleOwner(), new Observer<List<StudentTrackRecord>>() {
            @Override
            public void onChanged(List<StudentTrackRecord> studentTrackRecords) {

                adapter.updateList(studentTrackRecords);
            }


        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MEMBER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("updated_member_name");
            StudentTrackRecord newRecord = new StudentTrackRecord(name, 0, 0, 0, 0, "");
            mStudentTrackRecords.add(newRecord);
            mRecyclerView.getAdapter().notifyItemInserted(mStudentTrackRecords.size());

            SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
            viewModel.setStudentTrackRecordList(mStudentTrackRecords);
        }
    }

    private static class StudentTrackRecordAdapter extends RecyclerView.Adapter<StudentTrackRecordAdapter.StudentTrackRecordViewHolder> {

        private List<StudentTrackRecord> mStudentTrackRecords;

        public StudentTrackRecordAdapter(List<StudentTrackRecord> studentTrackRecords) {
            mStudentTrackRecords = studentTrackRecords;
        }

        @NonNull
        @Override
        public StudentTrackRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflate the layout for the RecyclerView item
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_student_track_record, parent, false);
            return new StudentTrackRecordViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentTrackRecordViewHolder holder, int position) {
            // Bind the data for the RecyclerView item
            StudentTrackRecord studentTrackRecord = mStudentTrackRecords.get(position);
            holder.mStudentNameTextView.setText(studentTrackRecord.getName());
            holder.mPresentValueTextView.setText(String.valueOf(studentTrackRecord.getPresent()));
            holder.mAbsentValueTextView.setText(String.valueOf(studentTrackRecord.getAbsent()));
            holder.mLateValueTextView.setText(String.valueOf(studentTrackRecord.getLate()));

        }

        @Override
        public int getItemCount() {
            return mStudentTrackRecords.size();
        }

        public void updateList(List<StudentTrackRecord> newList) {
            this.mStudentTrackRecords = newList;
            notifyDataSetChanged();
        }

        public static class StudentTrackRecordViewHolder extends RecyclerView.ViewHolder {

            public TextView mStudentNameTextView;
            public TextView mPresentValueTextView;
            public TextView mAbsentValueTextView;
            public TextView mLateValueTextView;
            public TextView mStreakValueTextView;
            public TextView mLevelValueTextView;

            public StudentTrackRecordViewHolder(@NonNull View itemView) {
                super(itemView);
                mStudentNameTextView = itemView.findViewById(R.id.text_student_name);
                mPresentValueTextView = itemView.findViewById(R.id.text_present_value);
                mAbsentValueTextView = itemView.findViewById(R.id.text_absent_value);
                mLateValueTextView = itemView.findViewById(R.id.text_late_value);


                //Add delete button logic here.
            }
        }
    }
}