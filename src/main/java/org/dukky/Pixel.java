package org.dukky;

import java.awt.*;

public class Pixel {

    private int red;
    private int green;
    private int blue;
    private int alpha;

    public Pixel(int r, int g, int b, int a) {
        setRed(r);
        setGreen(g);
        setBlue(b);
        setAlpha(a);
    }

    public Pixel(Color color) {
        this(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    @Override
    public String toString() {
        return new StringBuilder("{R:").append(getRed())
                .append(" G:").append(getGreen())
                .append(" B:").append(getBlue())
                .append(" A:").append(getAlpha()).append("}").toString();
    }

    public void invert () {
        setRed(255-this.red);
        setGreen(255-this.green);
        setBlue(255-this.blue);
    }

    public void greyscale () {
        double intensity = (double) (this.red + this.green + this.blue)/3;
        setRed((int) intensity);
        setGreen((int) intensity);
        setBlue((int) intensity);
    }

    public void protonopia () {
        if (this.red > 150) {
            setRed((int) (this.red*1.9));
        } else if (this.red < 120) {
            setRed((int) (this.red*1.5));
        } else {
            setRed((int) (this.red*1.25));
        }
        if (this.green > 175) {
            setGreen((int) (this.green - this.green*0.1));
        } else if (this.green > 100) {
            setGreen((int) (this.green - this.green*0.3));
        } else {
            setGreen((int) (this.green - this.green*0.2));
        }
        if (this.blue > 100 || this.blue == 0) {
            setBlue((int) (this.blue*0.9));
        } else if (this.blue < 20) {
            setBlue(this.blue + 60);
        } else {
            setBlue(0);
        }
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        if (red < 0 ) this.red = 0;
        else this.red = Math.min(red, 255);
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        if (green < 0 ) this.green = 0;
        else this.green = Math.min(green, 255);
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        if (blue < 0 ) this.blue = 0;
        else this.blue = Math.min(blue, 255);
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        if (alpha < 0 ) this.alpha = 0;
        else this.alpha = Math.min(alpha, 255);
    }
}
