/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koreanmake;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kimhy
 */
public class BrailieKorean {
    private static Map<Integer,Integer> chosung;
    private static Map<Integer,Integer> jungsung;
    private static Map<Integer,Integer> jongsung;
    
    /*private char[] Chosung = {'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
    private char[] Jungsung = {'ㅏ','ㅐ','ㅑ','ㅒ','ㅓ','ㅔ','ㅕ','ㅖ','ㅗ','ㅘ','ㅙ','ㅚ','ㅛ','ㅜ','ㅝ','ㅞ','ㅟ','ㅠ','ㅡ','ㅢ','ㅣ'};
    private char[] Jongsung = {' ','ㄱ','ㄲ','ㄳ','ㄴ','ㄵ','ㄶ','ㄷ','ㄹ','ㄺ','ㄻ','ㄼ',
        'ㄽ','ㄾ','ㄿ','ㅀ','ㅁ','ㅂ','ㅄ','ㅅ','ㅆ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
    */
    private static void chosung_init(){
        chosung=new HashMap<Integer,Integer>();
        chosung.put(8,0); //ㄱ ㄲ
        chosung.put(9,2); //ㄴ
        chosung.put(10,3); //ㄷ ㄸ
        chosung.put(16,5); //ㄹ
        chosung.put(17,6); //ㅁ
        chosung.put(24,7); //ㅂ ㅃ
        chosung.put(32,9); //ㅅ ㅆ
        chosung.put(40,12); //ㅈ ㅉ
        chosung.put(48,14); //ㅊ
        chosung.put(11,15); //ㅋ
        chosung.put(19,16); //ㅌ
        chosung.put(25,17); //ㅍ
        chosung.put(26,18); //ㅎ
    }
    private static void jungsung_init(){
        jungsung=new HashMap<Integer,Integer>();
        jungsung.put(35,0); //ㅏ
        jungsung.put(28,2); //ㅑ ㅒ
        jungsung.put(14,4); //ㅓ
        jungsung.put(49,6); //ㅕ
        jungsung.put(37,8); //ㅗ
        jungsung.put(44,12); //ㅛ
        jungsung.put(13,13); //ㅜ ㅟ
        jungsung.put(41,17); //ㅠ
        jungsung.put(42,18); //ㅡ
        jungsung.put(21,20); //ㅣ
        jungsung.put(20,1); //ㅐ
        jungsung.put(29,5); //ㅔ
        jungsung.put(12,7); //ㅖ
        jungsung.put(39,9); //ㅘ ㅙ
        jungsung.put(61,11); //ㅚ
        jungsung.put(15,14); //ㅝ ㅞ
        jungsung.put(38,19); //ㅢ
    }
    private static void jongsung_init(){
        jongsung=new HashMap<Integer,Integer>();
        jongsung.put(1,1); //ㄱ ㄲ ㄳ
        jongsung.put(18,4); //ㄴ ㄵ ㄶ
        jongsung.put(20,7); //ㄷ 
        jongsung.put(2,8); //ㄹ ㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ
        jongsung.put(34,16); //ㅁ
        jongsung.put(3,17); //ㅂ ㅄ
        jongsung.put(4,19); //ㅅ ㅆ
        jongsung.put(54,21); //ㅇ
        jongsung.put(5,22); //ㅈ
        jongsung.put(6,23); //ㅊ
        jongsung.put(22,24); //ㅋ
        jongsung.put(38,25); //ㅌ
        jongsung.put(50,26); //ㅍ
        jongsung.put(52,27); //ㅎ
    }
    
    static{
        chosung_init();
        jungsung_init();
        jongsung_init();
    }
    
    private static char CombineKorean(int chosung,int jungsung,int jongsung){
        int x = (chosung * 21 * 28) + (jungsung * 28) + jongsung;
        return (char)(x + 0xAC00);
    }
    
    public static String CombineBrailie(int data[]){
        int length=data.length;
        int i;
        String result="";
        int cache_cho=0,cache_jung=0,cache_jong=0,mode=0;
        //0 초성 1 중성 2 종성
        for(i=0;i<length;i++){
            switch(mode){
                case 0: //다음에 초성이 와야한다
                    if(!chosung.containsKey(data[i])){ //초성자가 아닐때
                        if(jungsung.containsKey(data[i])){ //중성자가 벌써왔다면 초성은 'ㅇ'
                            cache_cho=11;
                            i--;
                            mode++;
                            break;
                        }else
                            return result;
                    }
                    cache_cho=chosung.get(data[i]);
                    mode++;
                    break;
                case 1: //다음에 중성이 와야할때
                    if(!jungsung.containsKey(data[i]))
                        return result;
                    cache_jung=jungsung.get(data[i]);
                    mode++;
                    break;
                case 2:
                    if(!jongsung.containsKey(data[i])){ //종성자 위치
                        if(chosung.containsKey(data[i])){ //초성자가 왔다면
                            cache_jong=0; //받침 없음
                            result=result+CombineKorean(cache_cho,cache_jung,cache_jong);
                            i--;
                            mode=0;
                            break;
                        }
                    }
                    cache_jong=jongsung.get(data[i]); //받침 없음
                    result=result+CombineKorean(cache_cho,cache_jung,cache_jong);
                    mode=0;
                    cache_cho=0; cache_jung=0; cache_jong=0;
                    break;
            }
        }
        if(cache_cho!=0 && cache_jung!=0)
            result=result+CombineKorean(cache_cho,cache_jung,0);
        return result;
    }
}
