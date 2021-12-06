package ca.nscc.blackjackapp.ui.game;
import ca.nscc.blackjackapp.R;
import ca.nscc.blackjackapp.databinding.FragmentGameBinding;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.GnssAntennaInfo;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFragment extends Fragment {
   private TextView playerName, scoreLbl, dealerScoreLbl, topMessage, bottomMessage;
   private Button btnStart, btnHit, btnStay, btnPlayAgain;
   private ImageView dealer1, dealer2, dealer3, dealer4, dealer5, player1, player2, player3, player4, player5;

   private String name;

   private ImageView[] dealerArray = new ImageView[5];
   private ImageView[] playerArray = new ImageView[5];

    private int dealerScore = 0;
    private int playerScore = 0;
    private int value = 0;
    private int playerCounter = 2;
    private int dealerCounter = 0;


    private GameViewModel mViewModel;
    private FragmentGameBinding binding;
    private SharedPreferences pref;


    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        binding = FragmentGameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //Link Variables
        playerName = (TextView) root.findViewById(R.id.playerName);


        //Shared pref to get Player Name
        pref = getActivity().getSharedPreferences("fragment_game", Context.MODE_PRIVATE);
        name = pref.getString("playerName", null);
        playerName.setText("Good luck " + name + "!");

        //GamePlay
        btnStart = (Button) root.findViewById(R.id.btnStart);
        btnHit = (Button) root.findViewById(R.id.btnHit);
        btnStay = (Button) root.findViewById(R.id.btnStay);
        btnPlayAgain = (Button) root.findViewById(R.id.btnPlayAgain);

        dealer1 = (ImageView) root.findViewById(R.id.dealerBack1);
        dealer2 = (ImageView) root.findViewById(R.id.dealerBack2);
        dealer3 = (ImageView) root.findViewById(R.id.dealerBack3);
        dealer4 = (ImageView) root.findViewById(R.id.dealerBack4);
        dealer5 = (ImageView) root.findViewById(R.id.dealerBack5);
        player1 = (ImageView) root.findViewById(R.id.playerBack1);
        player2 = (ImageView) root.findViewById(R.id.playerBack2);
        player3 = (ImageView) root.findViewById(R.id.playerBack3);
        player4 = (ImageView) root.findViewById(R.id.playerBack4);
        player5 = (ImageView) root.findViewById(R.id.playerBack5);
        scoreLbl = (TextView) root.findViewById(R.id.scoreLbl);
        dealerScoreLbl = (TextView) root.findViewById(R.id.dealerScoreLbl);
        topMessage = (TextView) root.findViewById(R.id.topMessage);
        bottomMessage = (TextView) root.findViewById(R.id.bottomMessage);

        dealerArray[0] = dealer1;
        dealerArray[1] = dealer2;
        dealerArray[2] = dealer3;
        dealerArray[3] = dealer4;
        dealerArray[4] = dealer5;

        playerArray[0] = player1;
        playerArray[1] = player2;
        playerArray[2] = player3;
        playerArray[3] = player4;
        playerArray[4] = player5;


        //Start Button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make everything visible
                playerScore = 0;
                dealerScore = 0;
                value = 0;
                startGame();
            }
        });

        //Hit Button
        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Hit Button is clicked", Toast.LENGTH_SHORT).show();
                playerHit();


            }
        });

        //Button Stay
        btnStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Stay Button is clicked", Toast.LENGTH_SHORT).show();
                btnHit.setVisibility(View.INVISIBLE);
                btnStay.setVisibility(View.INVISIBLE);
                dealerPlay();


            }
        });

        //Button Play Again
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Play again button", Toast.LENGTH_SHORT).show();
                playAgain();
            }
        });



        return root;
    }


    private int getPicture(){

        List<Integer> picture = new ArrayList<>();

        picture.add(R.drawable.two_spades);
        picture.add(R.drawable.two_hearts);
        picture.add(R.drawable.two_diamonds);
        picture.add(R.drawable.two_clubs);
        picture.add(R.drawable.three_clubs);
        picture.add(R.drawable.three_spades);
        picture.add(R.drawable.three_diamonds);
        picture.add(R.drawable.three_hearts);
        picture.add(R.drawable.four_clubs);
        picture.add(R.drawable.four_hearts);
        picture.add(R.drawable.four_spades);
        picture.add(R.drawable.four_diamonds);
        picture.add(R.drawable.five_clubs);
        picture.add(R.drawable.five_hearts);
        picture.add(R.drawable.five_spades);
        picture.add(R.drawable.five_diamonds);
        picture.add(R.drawable.six_clubs);
        picture.add(R.drawable.six_hearts);
        picture.add(R.drawable.six_spades);
        picture.add(R.drawable.six_diamonds);
        picture.add(R.drawable.seven_clubs);
        picture.add(R.drawable.seven_hearts);
        picture.add(R.drawable.seven_spades);
        picture.add(R.drawable.seven_diamonds);
        picture.add(R.drawable.eight_clubs);
        picture.add(R.drawable.eight_hearts);
        picture.add(R.drawable.eight_spades);
        picture.add(R.drawable.eight_diamonds);
        picture.add(R.drawable.nine_clubs);
        picture.add(R.drawable.nine_hearts);
        picture.add(R.drawable.nine_spades);
        picture.add(R.drawable.nine_diamonds);
        picture.add(R.drawable.ten_clubs);
        picture.add(R.drawable.ten_hearts);
        picture.add(R.drawable.ten_spades);
        picture.add(R.drawable.ten_diamonds);
        picture.add(R.drawable.jack_clubs);
        picture.add(R.drawable.jack_hearts);
        picture.add(R.drawable.jack_spades);
        picture.add(R.drawable.jack_diamonds);
        picture.add(R.drawable.queen_clubs);
        picture.add(R.drawable.queen_hearts);
        picture.add(R.drawable.queen_spades);
        picture.add(R.drawable.queen_diamonds);
        picture.add(R.drawable.king_clubs);
        picture.add(R.drawable.king_hearts);
        picture.add(R.drawable.king_spades);
        picture.add(R.drawable.king_diamonds);
        picture.add(R.drawable.ace_clubs);
        picture.add(R.drawable.ace_hearts);
        picture.add(R.drawable.ace_spades2);
        picture.add(R.drawable.ace_diamonds);


        Random random = new Random();
        int num = random.nextInt(picture.size());
        return  picture.get(num);

    }

    private void startGame(){
        btnStart.setVisibility(View.INVISIBLE);
        btnHit.setVisibility(View.VISIBLE);
        btnStay.setVisibility(View.VISIBLE);
        dealer1.setVisibility(View.VISIBLE);
        dealer2.setVisibility(View.VISIBLE);
        dealer3.setVisibility(View.VISIBLE);
        dealer4.setVisibility(View.VISIBLE);
        dealer5.setVisibility(View.VISIBLE);
        player1.setVisibility(View.VISIBLE);
        player2.setVisibility(View.VISIBLE);
        player3.setVisibility(View.VISIBLE);
        player4.setVisibility(View.VISIBLE);
        player5.setVisibility(View.VISIBLE);
        scoreLbl.setVisibility(View.VISIBLE);
        dealerScoreLbl.setVisibility(View.VISIBLE);

        //Player Cards on Start
        for (int i = 0; i < 2; i++) {
            switch (getPicture()) {
                case R.drawable.two_clubs:
                    playerArray[i].setImageResource(R.drawable.two_clubs);
                    value = 2;
                    break;
                case R.drawable.two_hearts:
                    playerArray[i].setImageResource(R.drawable.two_hearts);
                    value = 2;
                    break;
                case R.drawable.two_spades:
                    playerArray[i].setImageResource(R.drawable.two_spades);
                    value = 2;
                    break;
                case R.drawable.two_diamonds:
                    playerArray[i].setImageResource(R.drawable.two_diamonds);
                    value = 2;
                    break;
                //====================================
                case R.drawable.three_clubs:
                    playerArray[i].setImageResource(R.drawable.three_clubs);
                    value = 3;
                    break;
                case R.drawable.three_hearts:
                    playerArray[i].setImageResource(R.drawable.three_hearts);
                    value = 3;
                    break;
                case R.drawable.three_spades:
                    playerArray[i].setImageResource(R.drawable.three_spades);
                    value = 3;
                    break;
                case R.drawable.three_diamonds:
                    playerArray[i].setImageResource(R.drawable.three_diamonds);
                    value = 3;
                    break;
                //====================================
                case R.drawable.four_clubs:
                    playerArray[i].setImageResource(R.drawable.four_clubs);
                    value = 4;
                    break;
                case R.drawable.four_hearts:
                    playerArray[i].setImageResource(R.drawable.four_hearts);
                    value = 4;
                    break;
                case R.drawable.four_spades:
                    playerArray[i].setImageResource(R.drawable.four_spades);
                    value = 4;
                    break;
                case R.drawable.four_diamonds:
                    playerArray[i].setImageResource(R.drawable.four_diamonds);
                    value = 4;
                    break;
                //====================================
                case R.drawable.five_clubs:
                    playerArray[i].setImageResource(R.drawable.five_clubs);
                    value = 5;
                    break;
                case R.drawable.five_hearts:
                    playerArray[i].setImageResource(R.drawable.five_hearts);
                    value = 5;
                    break;
                case R.drawable.five_spades:
                    playerArray[i].setImageResource(R.drawable.five_spades);
                    value = 5;
                    break;
                case R.drawable.five_diamonds:
                    playerArray[i].setImageResource(R.drawable.five_diamonds);
                    value = 5;
                    break;
                //====================================
                case R.drawable.six_clubs:
                    playerArray[i].setImageResource(R.drawable.six_clubs);
                    value = 6;
                    break;
                case R.drawable.six_hearts:
                    playerArray[i].setImageResource(R.drawable.six_hearts);
                    value = 6;
                    break;
                case R.drawable.six_spades:
                    playerArray[i].setImageResource(R.drawable.six_spades);
                    value = 6;
                    break;
                case R.drawable.six_diamonds:
                    playerArray[i].setImageResource(R.drawable.six_diamonds);
                    value = 6;
                    break;
                //====================================
                case R.drawable.seven_clubs:
                    playerArray[i].setImageResource(R.drawable.seven_clubs);
                    value = 7;
                    break;
                case R.drawable.seven_hearts:
                    playerArray[i].setImageResource(R.drawable.seven_hearts);
                    value = 7;
                    break;
                case R.drawable.seven_spades:
                    playerArray[i].setImageResource(R.drawable.seven_spades);
                    value = 7;
                    break;
                case R.drawable.seven_diamonds:
                    playerArray[i].setImageResource(R.drawable.seven_diamonds);
                    value = 7;
                    break;
                //====================================
                case R.drawable.eight_clubs:
                    playerArray[i].setImageResource(R.drawable.eight_clubs);
                    value = 8;
                    break;
                case R.drawable.eight_hearts:
                    playerArray[i].setImageResource(R.drawable.eight_hearts);
                    value = 8;
                    break;
                case R.drawable.eight_spades:
                    playerArray[i].setImageResource(R.drawable.eight_spades);
                    value = 8;
                    break;
                case R.drawable.eight_diamonds:
                    playerArray[i].setImageResource(R.drawable.eight_diamonds);
                    value = 8;
                    break;
                //====================================
                case R.drawable.nine_clubs:
                    playerArray[i].setImageResource(R.drawable.nine_clubs);
                    value = 9;
                    break;
                case R.drawable.nine_hearts:
                    playerArray[i].setImageResource(R.drawable.nine_hearts);
                    value = 9;
                    break;
                case R.drawable.nine_spades:
                    playerArray[i].setImageResource(R.drawable.nine_spades);
                    value = 9;
                    break;
                case R.drawable.nine_diamonds:
                    playerArray[i].setImageResource(R.drawable.nine_diamonds);
                    value = 9;
                    break;
                //====================================
                case R.drawable.ten_clubs:
                    playerArray[i].setImageResource(R.drawable.ten_clubs);
                    value = 10;
                    break;
                case R.drawable.ten_hearts:
                    playerArray[i].setImageResource(R.drawable.ten_hearts);
                    value = 10;
                    break;
                case R.drawable.ten_spades:
                    playerArray[i].setImageResource(R.drawable.ten_spades);
                    value = 10;
                    break;
                case R.drawable.ten_diamonds:
                    playerArray[i].setImageResource(R.drawable.ten_diamonds);
                    value = 10;
                    break;
                //====================================
                case R.drawable.jack_clubs:
                    playerArray[i].setImageResource(R.drawable.jack_clubs);
                    value = 10;
                    break;
                case R.drawable.jack_hearts:
                    playerArray[i].setImageResource(R.drawable.jack_hearts);
                    value = 10;
                    break;
                case R.drawable.jack_spades:
                    playerArray[i].setImageResource(R.drawable.jack_spades);
                    value = 10;
                    break;
                case R.drawable.jack_diamonds:
                    playerArray[i].setImageResource(R.drawable.jack_diamonds);
                    value = 10;
                    break;
                //====================================
                case R.drawable.queen_clubs:
                    playerArray[i].setImageResource(R.drawable.queen_clubs);
                    value = 10;
                    break;
                case R.drawable.queen_hearts:
                    playerArray[i].setImageResource(R.drawable.queen_hearts);
                    value = 10;
                    break;
                case R.drawable.queen_spades:
                    playerArray[i].setImageResource(R.drawable.queen_spades);
                    value = 10;
                    break;
                case R.drawable.queen_diamonds:
                    playerArray[i].setImageResource(R.drawable.queen_diamonds);
                    value = 10;
                    break;
                //====================================
                case R.drawable.king_clubs:
                    playerArray[i].setImageResource(R.drawable.king_clubs);
                    value = 10;
                    break;
                case R.drawable.king_hearts:
                    playerArray[i].setImageResource(R.drawable.king_hearts);
                    value = 10;
                    break;
                case R.drawable.king_spades:
                    playerArray[i].setImageResource(R.drawable.king_spades);
                    value = 10;
                    break;
                case R.drawable.king_diamonds:
                    playerArray[i].setImageResource(R.drawable.king_diamonds);
                    value = 10;
                    break;
                //====================================
                case R.drawable.ace_clubs:
                    playerArray[i].setImageResource(R.drawable.ace_clubs);
                    value = 11;
                    break;
                case R.drawable.ace_hearts:
                    playerArray[i].setImageResource(R.drawable.ace_hearts);
                    value = 11;
                    break;
                case R.drawable.ace_spades2:
                    playerArray[i].setImageResource(R.drawable.ace_spades2);
                    value = 11;
                    break;
                case R.drawable.ace_diamonds:
                    playerArray[i].setImageResource(R.drawable.ace_diamonds);
                    value = 11;
                    break;

            }

            playerScore = playerScore + value;
            scoreLbl.setText("Player Score: " + playerScore);
        } //End of player four loop

                /*/Only player cards at shown on start:
                //Dealer For Loop
                for (int i = 0; i < 1; i++) {
                    switch (getPicture()) {
                        case R.drawable.two_clubs:
                            dealerArray[i].setImageResource(R.drawable.two_clubs);
                            value = 2;
                            break;
                        case R.drawable.two_hearts:
                            dealerArray[i].setImageResource(R.drawable.two_hearts);
                            value = 2;
                            break;
                        case R.drawable.two_spades:
                            dealerArray[i].setImageResource(R.drawable.two_spades);
                            value = 2;
                            break;
                        case R.drawable.two_diamonds:
                            dealerArray[i].setImageResource(R.drawable.two_diamonds);
                            value = 2;
                            break;
                        //====================================
                        case R.drawable.three_clubs:
                            dealerArray[i].setImageResource(R.drawable.three_clubs);
                            value = 3;
                            break;
                        case R.drawable.three_hearts:
                            dealerArray[i].setImageResource(R.drawable.three_hearts);
                            value = 3;
                            break;
                        case R.drawable.three_spades:
                            dealerArray[i].setImageResource(R.drawable.three_spades);
                            value = 3;
                            break;
                        case R.drawable.three_diamonds:
                            dealerArray[i].setImageResource(R.drawable.three_diamonds);
                            value = 3;
                            break;
                        //====================================
                        case R.drawable.four_clubs:
                            dealerArray[i].setImageResource(R.drawable.four_clubs);
                            value = 4;
                            break;
                        case R.drawable.four_hearts:
                            dealerArray[i].setImageResource(R.drawable.four_hearts);
                            value = 4;
                            break;
                        case R.drawable.four_spades:
                            dealerArray[i].setImageResource(R.drawable.four_spades);
                            value = 4;
                            break;
                        case R.drawable.four_diamonds:
                            dealerArray[i].setImageResource(R.drawable.four_diamonds);
                            value = 4;
                            break;
                        //====================================
                        case R.drawable.five_clubs:
                            dealerArray[i].setImageResource(R.drawable.five_clubs);
                            value = 5;
                            break;
                        case R.drawable.five_hearts:
                            dealerArray[i].setImageResource(R.drawable.five_hearts);
                            value = 5;
                            break;
                        case R.drawable.five_spades:
                            dealerArray[i].setImageResource(R.drawable.five_spades);
                            value = 5;
                            break;
                        case R.drawable.five_diamonds:
                            dealerArray[i].setImageResource(R.drawable.five_diamonds);
                            value = 5;
                            break;
                        //====================================
                        case R.drawable.six_clubs:
                            dealerArray[i].setImageResource(R.drawable.six_clubs);
                            value = 6;
                            break;
                        case R.drawable.six_hearts:
                            dealerArray[i].setImageResource(R.drawable.six_hearts);
                            value = 6;
                            break;
                        case R.drawable.six_spades:
                            dealerArray[i].setImageResource(R.drawable.six_spades);
                            value = 6;
                            break;
                        case R.drawable.six_diamonds:
                            dealerArray[i].setImageResource(R.drawable.six_diamonds);
                            value = 6;
                            break;
                        //====================================
                        case R.drawable.seven_clubs:
                            dealerArray[i].setImageResource(R.drawable.seven_clubs);
                            value = 7;
                            break;
                        case R.drawable.seven_hearts:
                            dealerArray[i].setImageResource(R.drawable.seven_hearts);
                            value = 7;
                            break;
                        case R.drawable.seven_spades:
                            dealerArray[i].setImageResource(R.drawable.seven_spades);
                            value = 7;
                            break;
                        case R.drawable.seven_diamonds:
                            dealerArray[i].setImageResource(R.drawable.seven_diamonds);
                            value = 7;
                            break;
                        //====================================
                        case R.drawable.eight_clubs:
                            dealerArray[i].setImageResource(R.drawable.eight_clubs);
                            value = 8;
                            break;
                        case R.drawable.eight_hearts:
                            dealerArray[i].setImageResource(R.drawable.eight_hearts);
                            value = 8;
                            break;
                        case R.drawable.eight_spades:
                            dealerArray[i].setImageResource(R.drawable.eight_spades);
                            value = 8;
                            break;
                        case R.drawable.eight_diamonds:
                            dealerArray[i].setImageResource(R.drawable.eight_diamonds);
                            value = 8;
                            break;
                        //====================================
                        case R.drawable.nine_clubs:
                            dealerArray[i].setImageResource(R.drawable.nine_clubs);
                            value = 9;
                            break;
                        case R.drawable.nine_hearts:
                            dealerArray[i].setImageResource(R.drawable.nine_hearts);
                            value = 9;
                            break;
                        case R.drawable.nine_spades:
                            dealerArray[i].setImageResource(R.drawable.nine_spades);
                            value = 9;
                            break;
                        case R.drawable.nine_diamonds:
                            dealerArray[i].setImageResource(R.drawable.nine_diamonds);
                            value = 9;
                            break;
                        //====================================
                        case R.drawable.ten_clubs:
                            dealerArray[i].setImageResource(R.drawable.ten_clubs);
                            value = 10;
                            break;
                        case R.drawable.ten_hearts:
                            dealerArray[i].setImageResource(R.drawable.ten_hearts);
                            value = 10;
                            break;
                        case R.drawable.ten_spades:
                            dealerArray[i].setImageResource(R.drawable.ten_spades);
                            value = 10;
                            break;
                        case R.drawable.ten_diamonds:
                            dealerArray[i].setImageResource(R.drawable.ten_diamonds);
                            value = 10;
                            break;
                        //====================================
                        case R.drawable.jack_clubs:
                            dealerArray[i].setImageResource(R.drawable.jack_clubs);
                            value = 10;
                            break;
                        case R.drawable.jack_hearts:
                            dealerArray[i].setImageResource(R.drawable.jack_hearts);
                            value = 10;
                            break;
                        case R.drawable.jack_spades:
                            dealerArray[i].setImageResource(R.drawable.jack_spades);
                            value = 10;
                            break;
                        case R.drawable.jack_diamonds:
                            dealerArray[i].setImageResource(R.drawable.jack_diamonds);
                            value = 10;
                            break;
                        //====================================
                        case R.drawable.queen_clubs:
                            dealerArray[i].setImageResource(R.drawable.queen_clubs);
                            value = 10;
                            break;
                        case R.drawable.queen_hearts:
                            dealerArray[i].setImageResource(R.drawable.queen_hearts);
                            value = 10;
                            break;
                        case R.drawable.queen_spades:
                            dealerArray[i].setImageResource(R.drawable.queen_spades);
                            value = 10;
                            break;
                        case R.drawable.queen_diamonds:
                            dealerArray[i].setImageResource(R.drawable.queen_diamonds);
                            value = 10;
                            break;
                        //====================================
                        case R.drawable.king_clubs:
                            dealerArray[i].setImageResource(R.drawable.king_clubs);
                            value = 10;
                            break;
                        case R.drawable.king_hearts:
                            dealerArray[i].setImageResource(R.drawable.king_hearts);
                            value = 10;
                            break;
                        case R.drawable.king_spades:
                            dealerArray[i].setImageResource(R.drawable.king_spades);
                            value = 10;
                            break;
                        case R.drawable.king_diamonds:
                            dealerArray[i].setImageResource(R.drawable.king_diamonds);
                            value = 10;
                            break;
                        //====================================
                        case R.drawable.ace_clubs:
                            dealerArray[i].setImageResource(R.drawable.ace_clubs);
                            value = 11;
                            break;
                        case R.drawable.ace_hearts:
                            dealerArray[i].setImageResource(R.drawable.ace_hearts);
                            value = 11;
                            break;
                        case R.drawable.ace_spades2:
                            dealerArray[i].setImageResource(R.drawable.ace_spades2);
                            value = 11;
                            break;
                        case R.drawable.ace_diamonds:
                            dealerArray[i].setImageResource(R.drawable.ace_diamonds);
                            value = 11;
                            break;

                    }

                    dealerScore = dealerScore + value;
                    dealerScoreLbl.setText("Dealer Score: " + dealerScore);
                } //*/

    }

    private void playAgain(){
        btnPlayAgain.setVisibility(View.INVISIBLE);
        dealerScore = 0;
        playerScore = 0;
        value = 0;
        playerCounter = 2;
        dealerCounter = 0;

        dealerScoreLbl.setText("Dealer Score: 0");
        scoreLbl.setText("Player Score: 0");
        topMessage.setText("Let Play Blackjack");
        bottomMessage.setVisibility(View.INVISIBLE);
        playerName.setText("Good luck " + name);

        for(int i = 0; i < 5; i++) {
            playerArray[i].setImageResource(R.drawable.back_card);
        }
        for(int i = 0; i < 5; i++) {
            dealerArray[i].setImageResource(R.drawable.back_card);
        }

        startGame();

    }

    private void endGame(){
        btnHit.setVisibility(View.INVISIBLE);
        btnStay.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.VISIBLE);
    }

    private void dealerPlay(){
        //Dealer For Loop

        while (dealerScore < 21 || dealerCounter < 5 || dealerScore < playerScore) {
            if(dealerScore >= playerScore){
                //Toast.makeText(getContext(), "DEALER WINS", Toast.LENGTH_SHORT).show();
                topMessage.setText("DEALER WINS!");
                bottomMessage.setText("DEALER WINS!");
                bottomMessage.setVisibility(View.VISIBLE);
                playerName.setText("Better luck next time " + name + "!");
                endGame();
                break;
            }


            //GetPicture
            switch (getPicture()) {
                case R.drawable.two_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.two_clubs);
                    value = 2;
                    break;
                case R.drawable.two_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.two_hearts);
                    value = 2;
                    break;
                case R.drawable.two_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.two_spades);
                    value = 2;
                    break;
                case R.drawable.two_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.two_diamonds);
                    value = 2;
                    break;
                //====================================
                case R.drawable.three_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.three_clubs);
                    value = 3;
                    break;
                case R.drawable.three_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.three_hearts);
                    value = 3;
                    break;
                case R.drawable.three_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.three_spades);
                    value = 3;
                    break;
                case R.drawable.three_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.three_diamonds);
                    value = 3;
                    break;
                //====================================
                case R.drawable.four_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.four_clubs);
                    value = 4;
                    break;
                case R.drawable.four_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.four_hearts);
                    value = 4;
                    break;
                case R.drawable.four_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.four_spades);
                    value = 4;
                    break;
                case R.drawable.four_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.four_diamonds);
                    value = 4;
                    break;
                //====================================
                case R.drawable.five_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.five_clubs);
                    value = 5;
                    break;
                case R.drawable.five_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.five_hearts);
                    value = 5;
                    break;
                case R.drawable.five_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.five_spades);
                    value = 5;
                    break;
                case R.drawable.five_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.five_diamonds);
                    value = 5;
                    break;
                //====================================
                case R.drawable.six_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.six_clubs);
                    value = 6;
                    break;
                case R.drawable.six_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.six_hearts);
                    value = 6;
                    break;
                case R.drawable.six_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.six_spades);
                    value = 6;
                    break;
                case R.drawable.six_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.six_diamonds);
                    value = 6;
                    break;
                //====================================
                case R.drawable.seven_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.seven_clubs);
                    value = 7;
                    break;
                case R.drawable.seven_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.seven_hearts);
                    value = 7;
                    break;
                case R.drawable.seven_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.seven_spades);
                    value = 7;
                    break;
                case R.drawable.seven_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.seven_diamonds);
                    value = 7;
                    break;
                //====================================
                case R.drawable.eight_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.eight_clubs);
                    value = 8;
                    break;
                case R.drawable.eight_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.eight_hearts);
                    value = 8;
                    break;
                case R.drawable.eight_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.eight_spades);
                    value = 8;
                    break;
                case R.drawable.eight_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.eight_diamonds);
                    value = 8;
                    break;
                //====================================
                case R.drawable.nine_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.nine_clubs);
                    value = 9;
                    break;
                case R.drawable.nine_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.nine_hearts);
                    value = 9;
                    break;
                case R.drawable.nine_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.nine_spades);
                    value = 9;
                    break;
                case R.drawable.nine_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.nine_diamonds);
                    value = 9;
                    break;
                //====================================
                case R.drawable.ten_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.ten_clubs);
                    value = 10;
                    break;
                case R.drawable.ten_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.ten_hearts);
                    value = 10;
                    break;
                case R.drawable.ten_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.ten_spades);
                    value = 10;
                    break;
                case R.drawable.ten_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.ten_diamonds);
                    value = 10;
                    break;
                //====================================
                case R.drawable.jack_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.jack_clubs);
                    value = 10;
                    break;
                case R.drawable.jack_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.jack_hearts);
                    value = 10;
                    break;
                case R.drawable.jack_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.jack_spades);
                    value = 10;
                    break;
                case R.drawable.jack_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.jack_diamonds);
                    value = 10;
                    break;
                //====================================
                case R.drawable.queen_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.queen_clubs);
                    value = 10;
                    break;
                case R.drawable.queen_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.queen_hearts);
                    value = 10;
                    break;
                case R.drawable.queen_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.queen_spades);
                    value = 10;
                    break;
                case R.drawable.queen_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.queen_diamonds);
                    value = 10;
                    break;
                //====================================
                case R.drawable.king_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.king_clubs);
                    value = 10;
                    break;
                case R.drawable.king_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.king_hearts);
                    value = 10;
                    break;
                case R.drawable.king_spades:
                    dealerArray[dealerCounter].setImageResource(R.drawable.king_spades);
                    value = 10;
                    break;
                case R.drawable.king_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.king_diamonds);
                    value = 10;
                    break;
                //====================================
                case R.drawable.ace_clubs:
                    dealerArray[dealerCounter].setImageResource(R.drawable.ace_clubs);
                    value = 11;
                    break;
                case R.drawable.ace_hearts:
                    dealerArray[dealerCounter].setImageResource(R.drawable.ace_hearts);
                    value = 11;
                    break;
                case R.drawable.ace_spades2:
                    dealerArray[dealerCounter].setImageResource(R.drawable.ace_spades2);
                    value = 11;
                    break;
                case R.drawable.ace_diamonds:
                    dealerArray[dealerCounter].setImageResource(R.drawable.ace_diamonds);
                    value = 11;
                    break;

            }


            dealerCounter++;
            dealerScore = dealerScore + value;
            dealerScoreLbl.setText("Dealer Score: " + dealerScore);

            if(dealerScore > 21) {
                //Toast.makeText(getContext(), "BUST", Toast.LENGTH_SHORT).show();
                topMessage.setText("PLAYER WINS!");
                bottomMessage.setText("PLAYER WINS!");
                bottomMessage.setVisibility(View.VISIBLE);
                playerName.setText("You won " + name + "!");
                endGame();
                break;

            }

        }

    }

    private void playerHit(){
        //Player Cards on Hit
        switch (getPicture()) {
            case R.drawable.two_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.two_clubs);
                value = 2;
                break;
            case R.drawable.two_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.two_hearts);
                value = 2;
                break;
            case R.drawable.two_spades:
                playerArray[playerCounter].setImageResource(R.drawable.two_spades);
                value = 2;
                break;
            case R.drawable.two_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.two_diamonds);
                value = 2;
                break;
            //====================================
            case R.drawable.three_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.three_clubs);
                value = 3;
                break;
            case R.drawable.three_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.three_hearts);
                value = 3;
                break;
            case R.drawable.three_spades:
                playerArray[playerCounter].setImageResource(R.drawable.three_spades);
                value = 3;
                break;
            case R.drawable.three_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.three_diamonds);
                value = 3;
                break;
            //====================================
            case R.drawable.four_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.four_clubs);
                value = 4;
                break;
            case R.drawable.four_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.four_hearts);
                value = 4;
                break;
            case R.drawable.four_spades:
                playerArray[playerCounter].setImageResource(R.drawable.four_spades);
                value = 4;
                break;
            case R.drawable.four_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.four_diamonds);
                value = 4;
                break;
            //====================================
            case R.drawable.five_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.five_clubs);
                value = 5;
                break;
            case R.drawable.five_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.five_hearts);
                value = 5;
                break;
            case R.drawable.five_spades:
                playerArray[playerCounter].setImageResource(R.drawable.five_spades);
                value = 5;
                break;
            case R.drawable.five_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.five_diamonds);
                value = 5;
                break;
            //====================================
            case R.drawable.six_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.six_clubs);
                value = 6;
                break;
            case R.drawable.six_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.six_hearts);
                value = 6;
                break;
            case R.drawable.six_spades:
                playerArray[playerCounter].setImageResource(R.drawable.six_spades);
                value = 6;
                break;
            case R.drawable.six_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.six_diamonds);
                value = 6;
                break;
            //====================================
            case R.drawable.seven_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.seven_clubs);
                value = 7;
                break;
            case R.drawable.seven_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.seven_hearts);
                value = 7;
                break;
            case R.drawable.seven_spades:
                playerArray[playerCounter].setImageResource(R.drawable.seven_spades);
                value = 7;
                break;
            case R.drawable.seven_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.seven_diamonds);
                value = 7;
                break;
            //====================================
            case R.drawable.eight_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.eight_clubs);
                value = 8;
                break;
            case R.drawable.eight_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.eight_hearts);
                value = 8;
                break;
            case R.drawable.eight_spades:
                playerArray[playerCounter].setImageResource(R.drawable.eight_spades);
                value = 8;
                break;
            case R.drawable.eight_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.eight_diamonds);
                value = 8;
                break;
            //====================================
            case R.drawable.nine_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.nine_clubs);
                value = 9;
                break;
            case R.drawable.nine_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.nine_hearts);
                value = 9;
                break;
            case R.drawable.nine_spades:
                playerArray[playerCounter].setImageResource(R.drawable.nine_spades);
                value = 9;
                break;
            case R.drawable.nine_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.nine_diamonds);
                value = 9;
                break;
            //====================================
            case R.drawable.ten_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.ten_clubs);
                value = 10;
                break;
            case R.drawable.ten_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.ten_hearts);
                value = 10;
                break;
            case R.drawable.ten_spades:
                playerArray[playerCounter].setImageResource(R.drawable.ten_spades);
                value = 10;
                break;
            case R.drawable.ten_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.ten_diamonds);
                value = 10;
                break;
            //====================================
            case R.drawable.jack_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.jack_clubs);
                value = 10;
                break;
            case R.drawable.jack_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.jack_hearts);
                value = 10;
                break;
            case R.drawable.jack_spades:
                playerArray[playerCounter].setImageResource(R.drawable.jack_spades);
                value = 10;
                break;
            case R.drawable.jack_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.jack_diamonds);
                value = 10;
                break;
            //====================================
            case R.drawable.queen_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.queen_clubs);
                value = 10;
                break;
            case R.drawable.queen_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.queen_hearts);
                value = 10;
                break;
            case R.drawable.queen_spades:
                playerArray[playerCounter].setImageResource(R.drawable.queen_spades);
                value = 10;
                break;
            case R.drawable.queen_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.queen_diamonds);
                value = 10;
                break;
            //====================================
            case R.drawable.king_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.king_clubs);
                value = 10;
                break;
            case R.drawable.king_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.king_hearts);
                value = 10;
                break;
            case R.drawable.king_spades:
                playerArray[playerCounter].setImageResource(R.drawable.king_spades);
                value = 10;
                break;
            case R.drawable.king_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.king_diamonds);
                value = 10;
                break;
            //====================================
            case R.drawable.ace_clubs:
                playerArray[playerCounter].setImageResource(R.drawable.ace_clubs);
                value = 11;
                break;
            case R.drawable.ace_hearts:
                playerArray[playerCounter].setImageResource(R.drawable.ace_hearts);
                value = 11;
                break;
            case R.drawable.ace_spades2:
                playerArray[playerCounter].setImageResource(R.drawable.ace_spades2);
                value = 11;
                break;
            case R.drawable.ace_diamonds:
                playerArray[playerCounter].setImageResource(R.drawable.ace_diamonds);
                value = 11;
                break;

        }

        playerScore = playerScore + value;
        scoreLbl.setText("Player Score: " + playerScore);
        playerCounter += 1;

        if(playerScore > 21){
            //Toast.makeText(getContext(), "BUST", Toast.LENGTH_SHORT).show();
            topMessage.setText("DEALER WINS!");
            bottomMessage.setText("DEALER WINS!");
            bottomMessage.setVisibility(View.VISIBLE);
            playerName.setText("Better luck next time " + name + "!");
            endGame();
        }
    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        // TODO: Use the ViewModel
    }

}