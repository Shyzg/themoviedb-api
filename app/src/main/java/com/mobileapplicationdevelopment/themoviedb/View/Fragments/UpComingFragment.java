package com.mobileapplicationdevelopment.themoviedb.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapplicationdevelopment.themoviedb.Adapter.UpcomingAdapter;
import com.mobileapplicationdevelopment.themoviedb.Helper.ItemClickSupport;
import com.mobileapplicationdevelopment.themoviedb.Model.UpcomingModel;
import com.mobileapplicationdevelopment.themoviedb.R;
import com.mobileapplicationdevelopment.themoviedb.ViewModel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpComingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpComingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpComingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpComingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpComingFragment newInstance(String param1, String param2) {
        UpComingFragment fragment = new UpComingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView rv_up_coming;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_now_playing, container, false);
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        rv_up_coming = view.findViewById(R.id.rv_fragment_up_coming);
        MovieViewModel movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        movieViewModel.getUpComing();
        movieViewModel.getResultGetUpComing().observe(requireActivity(), showUpComing);

        return view;
    }

    private final Observer<UpcomingModel> showUpComing = new Observer<UpcomingModel>() {
        @Override
        public void onChanged(UpcomingModel upcomingModel) {
            rv_up_coming.setLayoutManager(new LinearLayoutManager(getActivity()));
            UpcomingAdapter adapter = new UpcomingAdapter(getActivity());
            adapter.setListUpComing(upcomingModel.getResults());
            rv_up_coming.setAdapter(adapter);
            ItemClickSupport.addTo(rv_up_coming).setOnItemLongClickListener((recyclerView, position, v) -> false);
            ItemClickSupport.addTo(rv_up_coming).setOnItemClickListener((recyclerView, position, v) -> {
                Bundle bundle = new Bundle();
                bundle.putString("movie_id", String.valueOf(upcomingModel.getResults().get(position).getId()));
                Navigation.findNavController(v).navigate(R.id.action_upcomingFragment_to_movieDetailsFragment, bundle);
            });
        }
    };
}