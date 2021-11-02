package com.mobileapplicationdevelopment.themoviedb.View.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobileapplicationdevelopment.themoviedb.Adapter.CreditsAdapter;
import com.mobileapplicationdevelopment.themoviedb.Helper.Const;
import com.mobileapplicationdevelopment.themoviedb.Model.CreditsModel;
import com.mobileapplicationdevelopment.themoviedb.Model.MoviesModel;
import com.mobileapplicationdevelopment.themoviedb.R;
import com.mobileapplicationdevelopment.themoviedb.ViewModel.MovieViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
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
        progressDialog = ProgressDialog.show(getActivity(), "", "Loading, Please Wait...", true);
        progressDialog.show();
    }

    private ImageView iv_backdrop, iv_poster;
    private TextView tv_title, tv_tagline, tv_year, tv_runtime, tv_rating, tv_genre, tv_overview;
    private LinearLayout ll_production_companies;
    private RecyclerView rv_credits;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        String movie_id = requireArguments().getString("movie_id");
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieViewModel.getMovieById(movie_id);
        movieViewModel.getResultGetMovieById().observe(getViewLifecycleOwner(), showMoviesResult);
        movieViewModel.getCredits(movie_id);
        movieViewModel.getResultGetCredits().observe(requireActivity(), showResultCredits);

        iv_backdrop = view.findViewById(R.id.iv_backdrop_fragment_movie_details);
        iv_poster = view.findViewById(R.id.iv_poster_fragment_movie_details);
        tv_title = view.findViewById(R.id.tv_title_fragment_movie_details);
        tv_tagline = view.findViewById(R.id.tv_tagline_fragment_movie_details);
        tv_year = view.findViewById(R.id.tv_year_fragment_movie_details);
        tv_runtime = view.findViewById(R.id.tv_runtime_fragment_movie_details);
        tv_rating = view.findViewById(R.id.tv_rating_fragment_movie_details);
        tv_genre = view.findViewById(R.id.tv_genre_fragment_movie_details);
        tv_overview = view.findViewById(R.id.tv_overview_fragment_movie_details);
        rv_credits = view.findViewById(R.id.rv_cast_fragment_movie_details);
        ll_production_companies = view.findViewById(R.id.ll_production_companies_logo_fragment_movie_details);

        return view;
    }

    private final Observer<MoviesModel> showMoviesResult = movies -> {

        SimpleDateFormat timeFormatter = new SimpleDateFormat("h'h' m'm'");
        SimpleDateFormat timeFormat = new SimpleDateFormat("m");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder genre = new StringBuilder();

//        toolbar.setTitle(movies.getTitle());
        for (int i = 0; i < movies.getGenres().size(); i++) {
            if (i == movies.getGenres().size() - 1) {
                genre.append(movies.getGenres().get(i).getName());
            } else {
                genre.append(movies.getGenres().get(i).getName()).append(", ");
            }
        }

        for (int x = 0; x < movies.getProduction_companies().size(); x++) {
            ImageView iv_productionCompanies = new ImageView(ll_production_companies.getContext());
            if (movies.getProduction_companies().get(x).getLogo_path() == null) {
                iv_productionCompanies.setImageResource(R.drawable.null_image);
            } else {
                Glide.with(requireActivity()).load(Const.IMG_URL + movies.getProduction_companies().get(x).getLogo_path()).into(iv_productionCompanies);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(208, 208);
            iv_productionCompanies.setLayoutParams(layoutParams);
            iv_productionCompanies.setBackgroundResource(R.drawable.background_text_view);
            layoutParams.setMargins(32, 0, 32, 0);
            iv_productionCompanies.setPadding(32, 32, 32, 32);
            ll_production_companies.addView(iv_productionCompanies);
            iv_productionCompanies.setOnClickListener(view -> {
                for (int y = 0; y < movies.getProduction_companies().size(); y++) {
                    Toast toast = Toast.makeText(getContext(), movies.getProduction_companies().get(y).getName(), Toast.LENGTH_SHORT);
                    toast.setText(movies.getProduction_companies().get(y).getName());
                    toast.show();
                }
            });
        }

        tv_title.setText(movies.getTitle());
        tv_tagline.setText(movies.getTagline());
        try {
            tv_year.setText(dateFormatter.format(Objects.requireNonNull(dateFormat.parse(movies.getRelease_date()))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            tv_runtime.setText(timeFormatter.format(Objects.requireNonNull(timeFormat.parse(String.valueOf(movies.getRuntime())))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_rating.setText(String.valueOf(movies.getVote_average()));
        tv_genre.setText(genre);
        Glide.with(requireActivity()).load(Const.IMG_URL + movies.getBackdrop_path()).into(iv_backdrop);
        Glide.with(requireActivity()).load(Const.IMG_URL + movies.getPoster_path()).into(iv_poster);
        tv_overview.setText(movies.getOverview());
    };

    private final Observer<CreditsModel> showResultCredits = new Observer<CreditsModel>() {
        @Override
        public void onChanged(CreditsModel creditsModel) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_credits.setLayoutManager(linearLayoutManager);
            CreditsAdapter creditsAdapter = new CreditsAdapter(getActivity());
            creditsAdapter.setCreditsList(creditsModel.getCast());
            rv_credits.setAdapter(creditsAdapter);
            rv_credits.setAdapter(creditsAdapter);
            progressDialog.dismiss();
        }
    };
}