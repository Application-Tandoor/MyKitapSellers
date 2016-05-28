package com.example.harshil.mykitapsellers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateStockFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateStockFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateStockFrag extends Fragment {

    @Bind(R.id.bookName) TextView _bookName;
    @Bind(R.id.isbnText) EditText _isbnText;
    @Bind(R.id.conditionSpinner) Spinner _conditionSpinner;

    @Bind(R.id.comparisonList) ListView _comparisonList;
    @Bind(R.id.cancelB) Button _cancelB;
    @Bind(R.id.commitB) Button _addB;

    @Bind(R.id.priceText) EditText _priceText;
    @Bind(R.id.quantText) EditText _quantityText;

    private static String conditionArr[]={"New", "As Good As New", "Moderately Used", "Used"};

    private static String condition;

    private static ArrayAdapter stockAdt;

//
    private OnFragmentInteractionListener mListener;

    public UpdateStockFrag() {
        // Required empty public constructor
    }

    public static UpdateStockFrag newInstance() {
        UpdateStockFrag fragment = new UpdateStockFrag();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_stock, container, false);

        ButterKnife.bind(this, view);

        ArrayAdapter<String> conditionAdt=new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, conditionArr);
        _conditionSpinner.setAdapter(conditionAdt);

        _conditionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                condition=conditionArr[position];

                //code for _comparisonList to be inserted here...

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

//    public void addBClicked(View v){
//
//        //perform necessary validation...
//        int price=Integer.parseInt(_priceText.getText().toString());
//        int quantity=Integer.parseInt(_quantityText.getText().toString());
//
//        if(quantity==0){
//            _quantityText.setError("Quantity can't be zero");
//            return;
//        }
//        if(_isbnText.length()<13){
//            _quantityText.setError("ISBN must be 13 charaacers long");
//            return;
//        }
//
//        _bookName.setText(getResources().getString(R.string.bookNameStr));
//        _priceText.setText(getResources().getString(R.string.priceStr));
//        _quantityText.setText(getResources().getString(R.string.quantityStr));
//
//        //add book details to corressponding objeccts, and stockList...
////
////
////
//    }
//
//    public void cancelBClicked(View v){
//
//        //reset price & quantity inputs...
//        _priceText.setText(getResources().getString(R.string.priceStr));
//        _quantityText.setText(getResources().getString(R.string.quantityStr));
//    }
//
//    public void resetBClicked(View v){
//
//        //reset all book details...
//        _bookName.setText(getResources().getString(R.string.bookNameStr));
//        _priceText.setText(getResources().getString(R.string.priceStr));
//        _quantityText.setText(getResources().getString(R.string.quantityStr));
//    }
//
//    public void fabBClicked(View v){
//
//        //inflate the UpdateListFrag fragment to enable user review its stock updates...
//        Fragment fragment = (Fragment)UpdateListFrag.newInstance();
//
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//    }
//
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
