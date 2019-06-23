package com.example.sameershekhar.petsearch.utils;

import com.example.sameershekhar.petsearch.models.Genre;

import java.util.List;

public class Constant {
    public static final String MOVIE_ID ="movie_id" ;

    //https://api.themoviedb.org/3/movie/343611?api_key={api_key}
    //https://api.themoviedb.org/3/discover/movie?api_key=bf92a7ebac398047e121a68f68cf5df3&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1
    public static String BASE_URL="https://api.themoviedb.org/3/";
    public static String BASE_IMAGE_URL="https://image.tmdb.org/t/p/w200";
    public static String API_KEY="bf92a7ebac398047e121a68f68cf5df3";
    private static String[] monthsOfYear= {"Jan" ," Feb" ,"March", "April" ,"May" ,"June","July" ,"August","Sep" ,"Oct" ,"Nov","Dec"};

    public static class Utils{
        public static String getFromatedMovieType(List<Genre> genres) {

            String movieType="";
            if(genres!=null){
                for(int i=0;i<genres.size();i++){
                    movieType += genres.get(i).getName()+", ";
                }
            }
            return movieType;

        }

        public static String getFromatedReleaseDate(String releaseData){
            String[] date=releaseData.split("-");
            int index=0;
            int day=1;
            if(releaseData!=null){
                if(date!=null && date.length ==3){
                    if(Integer.parseInt(date[1]) < 10){
                        index=Integer.parseInt(date[1])%10;
                    }else {
                        index=Integer.parseInt(date[1]);
                    }

                    if(Integer.parseInt(date[2]) < 10){
                        day=Integer.parseInt(date[2])%10;
                    }else {
                        day=Integer.parseInt(date[2]);
                    }
                }
            }

            return day+" "+monthsOfYear[index-1]+ " "+date[0];
        }

    }


}
