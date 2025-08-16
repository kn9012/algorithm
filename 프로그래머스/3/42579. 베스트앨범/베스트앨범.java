import java.util.*;

class Solution {
    class Music implements Comparable<Music> {
        int index;
        int play;
        String genre;
        
        Music (int index, int play, String genre) {
            this.index = index;
            this.play = play;
            this.genre = genre;
        }
        
        @Override
        public int compareTo(Music o) {
            if (this.play == o.play) {
                return Integer.compare(this.index, o.index);
            } else return Integer.compare(o.play, this.play);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        List<Music> musicList = new ArrayList<>();
        Map<String, Long> genrePlay = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genrePlay.put(genres[i], genrePlay.getOrDefault(genres[i], 0L) + plays[i]);
            musicList.add(new Music(i, plays[i], genres[i]));
        }
        
        List<String> sortedGenres = new ArrayList<>(genrePlay.keySet());
        sortedGenres.sort((a, b) -> Long.compare(genrePlay.get(b), genrePlay.get(a)));
        
        List<Integer> answer = new ArrayList<>();
        
        // 각 장르에서 최대 2곡 선택
        for (String genre : sortedGenres) {
            List<Music> list = new ArrayList<>();
            for (Music m : musicList) {
                if (m.genre.equals(genre)) list.add(m);
            }
            Collections.sort(list); // 재생 수 내림차순, 같으면 index 오름차순
            
            answer.add(list.get(0).index);
            if (list.size() > 1) answer.add(list.get(1).index);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}