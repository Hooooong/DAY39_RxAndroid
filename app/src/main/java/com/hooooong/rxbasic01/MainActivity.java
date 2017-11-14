package com.hooooong.rxbasic01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    // 데이터 저장 변수
    List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        customAdapter = new CustomAdapter();
        recyclerView.setAdapter(customAdapter);

        // 데이터 - 인터넷에서 순차적으로 가져오는것으로 정의
        String data[] = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

        // 1. 발행자 생성 : Operator From
        Observable<String> observableFrom = Observable.fromArray(data);
        // 1-1. 구독자
        /*observableFrom.subscribe(
                // onNext() : 데이터가 있으면 호출한다.
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        dataList.add(s);
                    }
                },
                // onError()
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                },
                // onComplete() 호출
                new Action() {
                    @Override
                    public void run() throws Exception {
                        customAdapter.setDataAndRefresh(dataList);
                    }
                });*/

        // 1-1. 구독자 (람다)
        observableFrom.subscribe(
                str->dataList.add(str),
                throwable -> {},
                () -> customAdapter.setDataAndRefresh(dataList)
        );

        // 2. 발행자 생성 : just()
        Observable<String> observableJust = Observable.just("JAN", "FEB", "MAR");
        // 2-1. 구독자 (람다)
        observableJust.subscribe(str->dataList.add(str));

        // 3. defer
        Observable<String> observableDefer = Observable.defer(() -> Observable.just("JAN", "FEB", "MAR"));
        observableDefer.subscribe(str->dataList.add(str), throwable -> {}, () -> customAdapter.setDataAndRefresh(dataList));


    }
}

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    List<String> data = new ArrayList<>();

    public void setDataAndRefresh(List<String> dataAndNotify) {
        this.data = dataAndNotify;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text1.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
        }
    }
}

