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

import com.mobileapplicationdevelopment.themoviedb.Adapter.NowPlayingAdapter;
import com.mobileapplicationdevelopment.themoviedb.Helper.ItemClickSupport;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.R;
import com.mobileapplicationdevelopment.themoviedb.ViewModel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance(String param1, String param2) {
        NowPlayingFragment fragment = new NowPlayingFragment();
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

    private RecyclerView rv_now_playing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_now_playing, container, false);
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        rv_now_playing = view.findViewById(R.id.rv_fragment_now_playing);
        MovieViewModel movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        movieViewModel.getNowPlaying();
        movieViewModel.getResultGetNowPlaying().observe(requireActivity(), showNowPlaying);

        return view;
    }

    private final Observer<NowPlayingModel> showNowPlaying = new Observer<NowPlayingModel>() {
        @Override
        public void onChanged(NowPlayingModel nowPlayingModel) {
            rv_now_playing.setLayoutManager(new LinearLayoutManager(getActivity()));
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setListNowPlaying(nowPlayingModel.getResults());
            rv_now_playing.setAdapter(adapter);
            ItemClickSupport.addTo(rv_now_playing).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                    return false;
                }
            });
            ItemClickSupport.addTo(rv_now_playing).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movie_id", String.valueOf(nowPlayingModel.getResults().get(position).getId()));
                    Navigation.findNavController(v).navigate(R.id.action_nowPlayingFragment_to_movieDetailsFragment, bundle);
                }
            });
        }
    };
}