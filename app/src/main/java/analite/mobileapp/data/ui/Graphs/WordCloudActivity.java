package analite.mobileapp.data.ui.Graphs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import net.alhazmy13.wordcloud.ColorTemplate;
import net.alhazmy13.wordcloud.WordCloud;
import net.alhazmy13.wordcloud.WordCloudView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import analite.mobileapp.R;

public class WordCloudActivity extends AppCompatActivity{

    private ImageButton back;

    List<WordCloud> list ;
    String text = "accidente estación granja sentido occidente oriente irresponsabilidad personas cayó empujaron Muere arrollado articulado permitido despreciada Peñalosa ciudad montañosa beneficios confiabilidad caos irresponsabilidad seguridad mal protestas colarse implementar política estricta sistema merecemos brinden respeto trabajadores servicio buses inseguro irregulares vías corrupción deficiente trabajo conductores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordcloud);

        back = (ImageButton) findViewById(R.id.word_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordCloudActivity.this.onBackPressed();
            }
        });

        generateRandomText();
        WordCloudView wordCloud = (WordCloudView) findViewById(R.id.wordCloud);
        wordCloud.setDataSet(list);
        wordCloud.setSize(580,380);
        wordCloud.setScale(50,10);
        wordCloud.setColors(ColorTemplate.MATERIAL_COLORS);
        wordCloud.notifyDataSetChanged();
    }

    private void generateRandomText() {
        String[] data = text.split(" ");
        list = new ArrayList<>();
        Random random = new Random();
        for (String s : data) {
            list.add(new WordCloud(s,random.nextInt(50)));
        }
        Log.d("Terms: ",String.valueOf(list.size()));
    }
}
