package com.yue_health.form;

public class BaseInfoForm {

    float height, weight, SP, DP, TG, GLU, CEA;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getSP() {
        return SP;
    }

    public void setSP(float SP) {
        this.SP = SP;
    }

    public float getDP() {
        return DP;
    }

    public void setDP(float DP) {
        this.DP = DP;
    }

    public float getTG() {
        return TG;
    }

    public void setTG(float TG) {
        this.TG = TG;
    }

    public float getGLU() {
        return GLU;
    }

    public void setGLU(float GLU) {
        this.GLU = GLU;
    }

    public float getCEA() {
        return CEA;
    }

    public void setCEA(float CEA) {
        this.CEA = CEA;
    }

    @Override
    public String toString() {
        return "BaseInfoForm{" +
                "height=" + height +
                ", weight=" + weight +
                ", SP=" + SP +
                ", DP=" + DP +
                ", TG=" + TG +
                ", GLU=" + GLU +
                ", CEA=" + CEA +
                '}';
    }
}
