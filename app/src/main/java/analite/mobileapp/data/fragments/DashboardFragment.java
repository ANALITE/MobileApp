package analite.mobileapp.data.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;

import analite.mobileapp.data.ui.Graphs.EmotionsPieActivity;
import analite.mobileapp.data.ui.Graphs.EmotionsIntensityActivity;
import analite.mobileapp.data.ui.Graphs.WordCloudActivity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import analite.mobileapp.R;
import analite.mobileapp.data.ui.Graphs.TweetsTrendActivity;


public class DashboardFragment extends Fragment {

    private View inflateView;

    private CardView mTrendGrpah;
    private CardView mPieGrpah;
    private CardView mBarGrpah;
    private CardView mWordCloud;

    private View progressView;
    private View resultView;
    private TextView resultTitle;
    private TextView analyzeTitle;

    private ImageButton search;
    private EditText hashtag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inflateView = inflater.inflate(R.layout.fragment_dashboard, null);

        componentsInitialization();
        actionListenersInitialization();

        return inflateView;
    }

    private void actionListenersInitialization() {
        mTrendGrpah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resultView.setVisibility(View.GONE);
                Intent intent= new Intent(getActivity(),TweetsTrendActivity.class );
                startActivity(intent);
            }
        });
        mPieGrpah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resultView.setVisibility(View.GONE);
                Intent intent= new Intent(getActivity(),EmotionsPieActivity.class );
                startActivity(intent);
            }
        });
        mBarGrpah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resultView.setVisibility(View.GONE);
                Intent intent= new Intent(getActivity(),EmotionsIntensityActivity.class );
                startActivity(intent);
            }
        });
        mWordCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resultView.setVisibility(View.GONE);
                Intent intent= new Intent(getActivity(),WordCloudActivity.class );
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analyze();
            }
        });
    }

    private void analyze() {
        analyzeTitle.setVisibility(View.GONE);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultTitle.setText("#Trasmilenio Result");
        resultTitle.setVisibility(View.VISIBLE);
        resultView.setVisibility(View.VISIBLE);
    }

    private void componentsInitialization() {
        mTrendGrpah = (CardView) inflateView.findViewById(R.id.TrendGraph_card);
        mPieGrpah = (CardView) inflateView.findViewById(R.id.PieGraph_card);
        mBarGrpah = (CardView) inflateView.findViewById(R.id.BarGraph_card);
        mWordCloud = (CardView) inflateView.findViewById(R.id.WordCloud_card);

        progressView = inflateView.findViewById(R.id.hash_tag_progress);
        resultView = inflateView.findViewById(R.id.result_view);
        resultTitle = (TextView) inflateView.findViewById(R.id.result_title);
        analyzeTitle = (TextView) inflateView.findViewById(R.id.analyze_title);

        search = (ImageButton) inflateView.findViewById(R.id.hash_tag_button);
        hashtag = (EditText) inflateView.findViewById(R.id.hash_tag_entry);
    }

}
