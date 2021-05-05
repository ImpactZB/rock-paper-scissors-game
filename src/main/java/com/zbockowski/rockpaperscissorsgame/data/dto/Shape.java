package com.zbockowski.rockpaperscissorsgame.data.dto;

public enum Shape {

    ROCK{
        @Override
        public Shape getStrongerShape(){
            return PAPER;
        }
    },
    PAPER{
        @Override
        public Shape getStrongerShape(){
            return SCISSORS;
        }
    },
    SCISSORS{
        @Override
        public Shape getStrongerShape(){
            return ROCK;
        }
    };

    public abstract Shape getStrongerShape();
}
