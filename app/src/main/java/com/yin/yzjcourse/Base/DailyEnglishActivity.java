package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yin.yzjcourse.Base.adapter.SentenceAdapter;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.EnglishSentenceEntity;
import com.yin.yzjcourse.utils.TranslationRequest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DailyEnglishActivity extends BaseActivity {
    private SentenceAdapter adapter;
    private RecyclerView rv;
    private List<EnglishSentenceEntity> sentenceEntityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_english);
        rv = findViewById(R.id.rv);
        sentenceEntityList = getSentenceList();
        adapter = new SentenceAdapter(R.layout.item_daily_english,sentenceEntityList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                sentenceEntityList.get(position);
                try {
                    String order = String.valueOf(position+1);
                    if((position+1)<10){
                        order = ("0"+order);
                    }
                    TranslationRequest.translate(order,sentenceEntityList.get(position).english);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public List<EnglishSentenceEntity> getSentenceList(){
        List<EnglishSentenceEntity> sentences = new ArrayList<>();
        sentences.add(new EnglishSentenceEntity(
                "",
                "You should stop playing with this",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Ding Ding did a great job.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Ding Ding has done very well.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "You did a great job.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "You've done very well.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Ding Ding helped Dad.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "You helped Dad.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Let's play Whack-a-mole.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Do you want to play Whack-a-mole?",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Would you like to play the Whac-A-Mole game?",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Let me teach you how to play.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "You should hit the one that suddenly pops up.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Let me demonstrate it for you one more time, like this, stand it up.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "It's Dad's turn.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Be careful\nBe cautious",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Dad wants it too, give it to Dad.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "What are you playing, Ding Ding? What are you playing?",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Wow, so high! So high!",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "There are still many breast milk storage bags left. These storage bags can be used as food storage bags.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Do we not need this?",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Can Dad give you a hug, okay?",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Ding Ding eats apples like he's cracking sunflower seeds.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Ding Ding has hidden some delicious snacks here.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Dad doesn't need anything else, no need to give anymore.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "The cup has water inside, Ding Ding. Let's play with this.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Ding Ding, Dad wants to play with the bubble machine. I want to play with the bubble machine.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Get me another one, please.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "It's 9 o'clock. Should we freshen up and get ready for bed? It's more comfortable to play in bed.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "It's not raining today. I'll go practice driving later.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "I'll go out after Ding Ding falls asleep.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Let's prepare some milk for Ding Ding.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "I didn't understand what you meant. Please go ahead and find it yourself.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Ding Ding wants to have some milk, but you'll have to wait for a while. It's still warming up.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "It's not good to always give in to her when she cries.",
                "xxxxxx"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "You go take a shower first.",
                "xxxxxx"));





        sentences.add(new EnglishSentenceEntity(
                "",
                "Tintin, let's go, Dad is taking you on a trip.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "You're craving again, you just had milk powder.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Tintin doesn't like to eat, Dad likes to eat.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "You'd better not sleep all morning.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Dingding is hungry, I'll prepare the formula milk.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "I'll feed you milk right away.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Okay, okay, okay, all done.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Here we go, oh my, so hungry all of a sudden!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "You see her eyes shining, and you're still saying she's not hungry?",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "I don't eat, you don't eat. I eat, and then you want to eat.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "More! More! More!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Daddy wants more! Daddy wants more!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Little pig, Dingding is a little pig!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "It's time for a diaper change.\n" +
                        "It's time for a butt wash.\n" +
                        "It's time to clean your bottom.\n" +
                        "It's time to wash your bottom",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Daddy forgot, we need to take off the pants first!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Dingding, what are you doing? Oh, you're playing a game!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Don't throw it, don't throw it on the ground.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Why are you throwing it on the ground?",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "If you throw it one more time, I won't pick it up.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Dingding can't throw it away, hehe!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "This time, Daddy has to catch it!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "I caught it!",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Let's try again, nope, one more time.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Wipe your nose, the bubbles are all out.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Wipe it again, wipe it again, oh dear, the more you wipe, the more there is.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Are you still biting, still biting?",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "It's not needed.",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "you are a foodie",
                "*************"));
        sentences.add(new EnglishSentenceEntity(
                "",
                "Dingding is tired.",
                "*************"));







        sentences.add(new EnglishSentenceEntity(
                "吐出来，吐出来",
                "Spit it out, spit it out.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "松手吧，丁丁",
                "Release your hand",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "你自己走嘛，不要牵着我",
                "Go by yourself, don't hold onto me.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "哎呀，那是垃圾啊",
                "Oh dear, that's rubbish.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "你咋啥都摸啊",
                "Why do you touch everything?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "我带你出去溜达一圈好不好",
                "Do you want to go for a stroll with me?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "喝一口",
                "Take a sip.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "你不穿衣服怎么出去啊，你不是要出去吗",
                "How are you going out without wearing clothes? Aren't you supposed to go out?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "妈妈马上就回来了",
                "Mom will be back soon.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "你不高兴啊，妈妈马上就过来了",
                "You're not happy, huh? Mom will be here soon.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "哇哦，什么声音？",
                "Wow, what's that sound?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "你饿了吗？是不是饿了？",
                "Are you hungry? Are you feeling hungry?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "找点零食吃",
                "Find some snacks to eat.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "给丁丁找点好吃的",
                "Find something tasty for Ding Ding.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "好吃吗，好吃的话爸爸再给你拿一个。",
                "Is it delicious? If it's delicious, Dad will get you another one.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "拿点水喝好不好？",
                "Would you like some water to drink?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "又看见啥了？",
                "What did you see again?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "你想干啥啊",
                "What do you want to do?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "丁丁喜欢这样啊",
                "Ding Ding likes it this way, huh?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "坐你的小椅子上吃好不好",
                "Is it okay to sit on your little chair and eat?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "擦一擦水",
                "Wipe away the water.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "怎么了，吃你的嘛",
                "What's wrong? Eat yours.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "吃吧，大口吃！",
                "Eat, take big bites!",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "你看到啥了，啥也没有啊",
                "What did you see? There's nothing there.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "正吃着奶，又去拿你的饼干？",
                "You're having milk, and now you want to get your cookies again?",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "别吃零食了，一会长蛀牙了",
                "Don't eat snacks anymore; you might get cavities later.",
                "2023年11月28日"));
        sentences.add(new EnglishSentenceEntity(
                "吃饱没",
                "Are you full?",
                "2023年11月29日"));
        sentences.add(new EnglishSentenceEntity(
                "这个衣服会走路啊",
                "Does this clothing walk?",
                "2023年11月29日"));
        sentences.add(new EnglishSentenceEntity(
                "丁丁往这走来",
                "Tintin, come this way",
                "2023年11月29日"));
        sentences.add(new EnglishSentenceEntity(
                "往这边拐，别往妈妈那去，那里都是水",
                "Turn this way, don't go towards mom, there's water over there.",
                "2023年11月29日"));





        sentences.add(new EnglishSentenceEntity(
                "丁丁穿新鞋了",
                "Ding Ding is wearing new shoes.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "起来",
                "stand up",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "爸爸教你怎么起来",
                "Dad teaches you how to stand up.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "纸不能吃，你啥都往嘴里放。",
                "You can't eat paper, why do you put everything into your mouth?",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "丁丁，你听",
                "Ding Ding, listen.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "爸爸的脚穿不上。",
                "Dad's feet can't fit in.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "另一只鞋在哪里？",
                "Where is the other shoe?",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "慢一点，丁丁",
                "Slow down, Ding Ding.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "别往外吐口水，丁丁。",
                "Don't spit outside, Ding Ding.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "等一下",
                "Wait a moment.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "丁丁来坐你的椅子",
                "Ding Ding, come and sit in your chair.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "你又想吃东西了",
                "You want to eat again.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "你走开",
                "You go away.",
                "2023年12月04日"));
        sentences.add(new EnglishSentenceEntity(
                "丁丁你挡着我了",
                "Ding Ding, you're blocking me.",
                "2023年12月04日"));
        return sentences;
    }
}
