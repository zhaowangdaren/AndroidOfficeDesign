package zy.ustc.edu.cn.androidofficedesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.support.v7.widget.RecyclerView.Adapter;

/**
 * Created by ustc-pc on 2015/7/17.
 */
public class RecyclerViewFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static RecyclerViewFragment newInstance(int sectionNumber){
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public RecyclerViewFragment(){

    }

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaceState){
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.my_recycler_view);

        initView();

        return rootView;
    }

    private void initView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        String[] dataset = new String[]{"Test1","Test2","Test3"};
        MyAdapter myAdapter = new MyAdapter(dataset);
        mRecyclerView.setAdapter(myAdapter);

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView mTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView)itemView.findViewById(R.id.text_view);
            }
        }

        private String[] mDataset;

        //Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset){
            mDataset = myDataset;
        }

        //Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            //create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        //Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position]);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
