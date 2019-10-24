package com.example.managerstudent_mvvm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerstudent_mvvm.R;
import com.example.managerstudent_mvvm.model.Student;
import com.example.managerstudent_mvvm.view.main.IOnclickItemListener;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private Context mContext;
    private List<Student> studentList;
    private IOnclickItemListener<Student> itemListener;

    public StudentAdapter(Context mContext, List<Student> studentList) {
        this.mContext = mContext;
        this.studentList = studentList;
//        if (mContext instanceof IOnclickItemListener) {
//            this.itemListener = (IOnclickItemListener) mContext;
//        }
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    public void setItemListener(IOnclickItemListener<Student> listener) {
        this.itemListener = listener;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_student, parent, false);
        return new MyViewHolder(view);

    }

    public Student getStudentAt(int position) {
        return studentList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
        final Student student = studentList.get(position);
        holder.tvName.setText(student.getName());
        holder.tvDate.setText(student.getBirth());
        holder.tvSex.setText(student.getSex());
    }
    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate;
        TextView tvSex;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txt_name_student);
            tvDate = itemView.findViewById(R.id.txt_date_student);
            tvSex = itemView.findViewById(R.id.txt_sex_student);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (itemListener != null && position != RecyclerView.NO_POSITION) {
                        itemListener.onClick(studentList.get(position));
                    }

                }
            });
        }
    }
}
