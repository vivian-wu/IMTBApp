package imtbteam.imtb;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class game_rule extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Button button1,button2,button3;

    private TextView textView7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        button1 = (Button) findViewById(R.id.gule_btn_start);
        button2 = (Button) findViewById(R.id.gule_btn_savings);
        button3= (Button) findViewById(R.id.gule_btn_entrepreneurship);
        textView7 = (TextView) findViewById(R.id.textView7);
        final TextView textView7 = (TextView)findViewById(R.id.textView7);
        textView7.setMovementMethod(ScrollingMovementMethod.getInstance());

        textView7.setText("");
        textView7.setText("一、\t遊戲準備\n" +
                "　　此遊戲建議人數最少為2人才能開始，最多上限為6人。\n" +
                "　　將遊戲圖版攤平置於桌上，將機會卡、命運卡、市場變化卡、天使卡、惡魔卡、投資理財卡、大/小訂單卡正面朝下洗勻後，形成多組卡牌堆，分別置於圖板上的對應位置上。\n" +
                "　　玩家們各選一代表色，並拿取對應顏色的人型標記，玩家將人型標記置於「累積資本圈」的起點處。\n" +
                "　　所有玩家打開遊戲APP，設定初始職業、想創業類別及選擇成功目標，並與其他玩家互相確認。\n" +
                "　　每位玩家輪流丟擲1顆骰子，由所擲點數最大者擔任起始玩家。從起始玩家開始，依順時針方向，各玩家輪流依序進行遊戲。\n" +
                "\n" +
                "二、遊戲進行\n" +
                "當「累積資本圈」內的玩家其資本大於創業資金目標「30萬」時，便可依圖板上的箭頭指示進入「創業圈」的格子內。\n" +
                "進入「創業圈」的玩家根據其在「累積資本圈」的最終資本，開始進行「創業圈」遊戲。\n" +
                "\n" +
                "\n" +
                "三、遊戲結束\n" +
                "　　遊戲進行時，若有任何一位玩家達成下列3個目標其中之一，遊戲即告結束，該玩家亦成為遊戲贏家。\n" +
                "\n" +
                "（1） 最先達成第一桶金目標「100萬」的玩家。\n" +
                "（2） 當玩家宣告破產時，該玩家結束遊戲。\n" +
                "（3） 當沒有玩家達到目標時，到達所設定的時間即結束遊戲，贏家則是離目標最近者。\n" +
                "\n" +
                "破產說明：\n" +
                "　　若玩家的現金不足以支付月支出，將宣告破產。玩家在宣告破產後，須以首期支付款一半的價格出售其所有資產給銀行。玩家利用此筆資金繼續遊戲，若無資金，則玩家將正式出局。\n"+
                "   ＊遊戲結束後，匯出現金資料，根據資料編製「現金流量表」。\n");

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textView7.setText("");
                textView7.setText("一、\t遊戲準備\n" +
                        "　　此遊戲建議人數最少為2人才能開始，最多上限為6人。\n" +
                        "　　將遊戲圖版攤平置於桌上，將機會卡、命運卡、市場變化卡、天使卡、惡魔卡、投資理財卡、大/小訂單卡正面朝下洗勻後，形成多組卡牌堆，分別置於圖板上的對應位置上。\n" +
                        "　　玩家們各選一代表色，並拿取對應顏色的人型標記，玩家將人型標記置於「累積資本圈」的起點處。\n" +
                        "　　所有玩家打開遊戲APP，設定初始職業、想創業類別及選擇成功目標，並與其他玩家互相確認。\n" +
                        "　　每位玩家輪流丟擲1顆骰子，由所擲點數最大者擔任起始玩家。從起始玩家開始，依順時針方向，各玩家輪流依序進行遊戲。\n" +
                        "\n" +
                        "二、遊戲進行\n" +
                        "當「累積資本圈」內的玩家其資本大於創業資金目標「30萬」時，便可依圖板上的箭頭指示進入「創業圈」的格子內。\n" +
                        "進入「創業圈」的玩家根據其在「累積資本圈」的最終資本，開始進行「創業圈」遊戲。\n" +
                        "\n" +
                        "\n" +
                        "三、遊戲結束\n" +
                        "　　遊戲時間為兩小時，遊戲進行時，若達成下列情境，遊戲即告結束。\n" +
                        "\n" +
                        "（1） 兩小時結束，擁有現金最多的玩家。\n" +
                        "（2） 當玩家宣告破產時，該玩家結束遊戲。\n" +
                        "（3） 當其餘玩家均宣告破產，則剩餘的玩家則為贏家。\n" +
                        "\n" +
                        "破產說明：\n" +
                        "　　若玩家的現金不足以支付月支出，將宣告破產。玩家在宣告破產後，須以首期支付款一半的價格出售其所有資產給銀行。玩家利用此筆資金繼續遊戲，若無資金，則玩家將正式出局。\n"+
                        "   ＊遊戲結束後，匯出現金資料，根據資料編製「現金流量表」。\n");
            } });
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textView7.setText("");
                textView7.setText("行動玩家丟擲1顆骰子，根據所擲1顆骰子點數，依箭頭指示方向往前移動對應格數，並根據自家人型標記所停留的格子，執行格子內敘述事項。\n" +
                        "\n" +
                        "（1） 機會(紅)格：可選擇抽取1張「機會」卡並掃描卡片上的QRCode，APP將自動記錄卡片內容。 \n" +
                        "（2） 命運(綠)格: 可選擇抽取1張「命運」卡並掃描卡片上的QRCode，APP將自動記錄卡片內容。 \n" +
                        "（3） 監獄(黑)格：玩家若走到「監獄格」，玩家必須將自己的人型標誌放置「監獄格」並休息三回。\n" +
                        "（4） 銀行發薪日(黃)格：可向銀行領取每月薪水，掃描「薪水QRCode」將薪水記錄至遊戲APP的現金中。若玩家經過(走到)此格時忘記領取(掃描卡片)，將喪失此次領取機會。\n" +
                        "（5） 脫魯(紫)格：走到此格獲得男(女)朋友，如重複走到每月開銷增加，一人限走三次。\n");
            } });
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textView7.setText("");
                textView7.setText("行動玩家丟擲1顆骰子，根據所擲1顆骰子點數，依箭頭指示方向往前移動對應格數，並根據自家人型標記所停留的格子，執行格子內敘述事項。\n" +
                        "\n" +
                        "（1）\t市場變化(灰)格：可選擇抽取1張「市場變化」卡並掃描卡片上的QRCode，APP將自動記錄卡片內容。此卡片可能對其他玩家造成影響。\n" +
                        "（2）\t天使(白)格: 可選擇抽取1張「天使」卡並掃描卡片上的QRCode，APP將自動記錄卡片內容。卡片內容對個人有利。\n" +
                        "（3）\t惡魔(紫)格: 可選擇抽取1張「惡魔」卡並掃描卡片上的QRCode，APP將自動記錄卡片內容。卡片內容對個人不利。\n" +
                        "（4）\t投資理財(粉紅)格: 可選擇抽取1張「投資理財」卡並掃描卡片上的QRCode，APP將自動記錄卡片內容。此卡可選擇是否購買或賣出資產。\n" +
                        "（5）\t大/小訂單(藍)格:可選擇抽取1張「大訂單」卡或「小訂單」卡 並掃描卡片上的QRCode，APP將自動記錄卡片內容。此卡可選擇是否接下此筆訂單，不接下訂單也會損失固定成本。\n" +
                        "（6）\t監獄(黑)格：玩家若走到「監獄格」，玩家必須將自己的人型標誌放置「監獄格」並休息三回。\n" +
                        "（7）\t結婚(紅)格：走到此格則結婚，結婚玩家需掃描「結婚卡QRCode」其他玩家每人必須支付紅包1200元，如重複走到則重新結婚一次，費用需再支付一次，一人限走三次。\n");
            } });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_rule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
