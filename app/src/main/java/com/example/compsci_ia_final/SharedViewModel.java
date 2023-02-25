package com.example.compsci_ia_final;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<StudentTrackRecord>> mStudentTrackRecordList = new MutableLiveData<>();

    public void setStudentTrackRecordList(List<StudentTrackRecord> studentTrackRecords) {
        mStudentTrackRecordList.setValue(studentTrackRecords);
    }



    public void updateStudentTrackRecord(ArrayList<StudentAttendance> studentAttendanceList) {
        ArrayList<StudentTrackRecord> updatedStudentTrackRecordList = new ArrayList<>(mStudentTrackRecordList.getValue());
        for (StudentAttendance studentAttendance : studentAttendanceList) {
            StudentTrackRecord studentTrackRecordIteration = updatedStudentTrackRecordList.get(studentAttendanceList.indexOf(studentAttendance));

            Log.d("SharedViewModel1", studentTrackRecordIteration.getName() + " " + studentTrackRecordIteration.getPresent() + " " + studentTrackRecordIteration.getAbsent() + " " + studentTrackRecordIteration.getLate());


            if (studentAttendance.isPresent()) {
                int updatedPresent = studentTrackRecordIteration.getPresent() + 1;
                studentTrackRecordIteration.setPresent(updatedPresent);
            }
            if (studentAttendance.isAbsent()) {
                int updatedAbsent = studentTrackRecordIteration.getAbsent() + 1;
                studentTrackRecordIteration.setAbsent(updatedAbsent);
            }
            if (studentAttendance.isLate()) {
                int updatedLate = studentTrackRecordIteration.getLate() + 1;
                studentTrackRecordIteration.setLate(updatedLate);
            }
        }
        for(StudentTrackRecord studentTrackRecord : updatedStudentTrackRecordList) {
            //print values in updatedStudentTrackRecordList
            Log.d("SharedViewModel2", studentTrackRecord.getName() + " " + studentTrackRecord.getPresent() + " " + studentTrackRecord.getAbsent() + " " + studentTrackRecord.getLate());
        }
        mStudentTrackRecordList.postValue(updatedStudentTrackRecordList);


    }

    public LiveData<List<StudentTrackRecord>> getStudentTrackRecordList() {
        return mStudentTrackRecordList;
    }
}
