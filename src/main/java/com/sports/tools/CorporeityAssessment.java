package com.sports.tools;

import com.sports.entity.Subhealthy;

public class CorporeityAssessment {

//    心肺能力评价
    public int getHeartLungAbility(int time,float sumnum){
        float assessmentNum=(time*100)/(2*sumnum);
            if(assessmentNum<48.5){
                return 1;
            }else if(assessmentNum<53.5){
                return 2;
            }else if(assessmentNum<62.4){
                return 3;
            }else if(assessmentNum<70.8){
                return 4;
            }else {
                return 5;
            }
    }
//    通过俯卧撑与仰卧起坐个数评测肌肉耐力
    public int getMuscularEndurance(String sex,int age,int fuwocheng,int yangwoqizuo){
        int fuwochengAssess=0;
        int yangwoqizuoAssess=0;

            if(age<20){
                if (sex.equals("男")){
                    if(fuwocheng<10) {
                        fuwochengAssess= 1;
                    }else if(fuwocheng<18) {
                        fuwochengAssess=2;
                    }else if(fuwocheng<34) {
                        fuwochengAssess=3;
                    }else if(fuwocheng<46) {
                        fuwochengAssess=4;
                    }else{
                        fuwochengAssess=5;
                    }

                }else {
                    if(fuwocheng<5) {
                        fuwochengAssess=1;
                    }else if(fuwocheng<10) {
                        fuwochengAssess=2;
                    }else if(fuwocheng<20) {
                        fuwochengAssess=3;
                    }else if(fuwocheng<27) {
                        fuwochengAssess=4;
                    }else{
                        fuwochengAssess=5;
                    }
                }

                if (sex.equals("男")){
                    if(yangwoqizuo<12) {
                        yangwoqizuoAssess= 1;
                    }else if(yangwoqizuo<21) {
                        yangwoqizuoAssess=2;
                    }else if(yangwoqizuo<33) {
                        yangwoqizuoAssess=3;
                    }else if(yangwoqizuo<40) {
                        yangwoqizuoAssess=4;
                    }else{
                        yangwoqizuoAssess=5;
                    }

                }else {
                    if(yangwoqizuo<7) {
                        yangwoqizuoAssess=1;
                    }else if(yangwoqizuo<16) {
                        yangwoqizuoAssess=2;
                    }else if(yangwoqizuo<28) {
                        yangwoqizuoAssess=3;
                    }else if(yangwoqizuo<35) {
                        yangwoqizuoAssess=4;
                    }else{
                        yangwoqizuoAssess=5;
                    }
                }
            }else if (age<30) {
                if (sex.equals("男")){
                    if(fuwocheng<9) {
                        fuwochengAssess= 1;
                    }else if(fuwocheng<16) {
                        fuwochengAssess=2;
                    }else if(fuwocheng<29) {
                        fuwochengAssess=3;
                    }else if(fuwocheng<39) {
                        fuwochengAssess=4;
                    }else{
                        fuwochengAssess=5;
                    }
                }else {
                    if(fuwocheng<6) {
                        fuwochengAssess= 1;
                    }else if(fuwocheng<11) {
                        fuwochengAssess=2;
                    }else if(fuwocheng<22) {
                        fuwochengAssess=3;
                    }else if(fuwocheng<29) {
                        fuwochengAssess=4;
                    }else{
                        fuwochengAssess=5;
                    }
                }


                if (sex.equals("男")){
                    if(yangwoqizuo<8) {
                        yangwoqizuoAssess= 1;
                    }else if(yangwoqizuo<16) {
                        yangwoqizuoAssess=2;
                    }else if(yangwoqizuo<24) {
                        yangwoqizuoAssess=3;
                    }else if(yangwoqizuo<32) {
                        yangwoqizuoAssess=4;
                    }else{
                        yangwoqizuoAssess=5;
                    }

                }else {
                    if(yangwoqizuo<3) {
                        yangwoqizuoAssess=1;
                    }else if(yangwoqizuo<11) {
                        yangwoqizuoAssess=2;
                    }else if(yangwoqizuo<19) {
                        yangwoqizuoAssess=3;
                    }else if(yangwoqizuo<27) {
                        yangwoqizuoAssess=4;
                    }else{
                        yangwoqizuoAssess=5;
                    }
                }
            }else if (age<40) {
                if (sex.equals("男")){
                    if(fuwocheng<7) {
                        fuwochengAssess= 1;
                    }else if(fuwocheng<12) {
                        fuwochengAssess=2;
                    }else if(fuwocheng<24) {
                        fuwochengAssess=3;
                    }else if(fuwocheng<33) {
                        fuwochengAssess=4;
                    }else{
                        fuwochengAssess=5;
                    }
                }else {
                    if(fuwocheng<4) {
                        fuwochengAssess= 1;
                    }else if(fuwocheng<9) {
                        fuwochengAssess=2;
                    }else if(fuwocheng<21) {
                        fuwochengAssess=3;
                    }else if(fuwocheng<30) {
                        fuwochengAssess=4;
                    }else{
                        fuwochengAssess=5;
                    }
                }


                if (sex.equals("男")){
                    if(yangwoqizuo<5) {
                        yangwoqizuoAssess= 1;
                    }else if(yangwoqizuo<10) {
                        yangwoqizuoAssess=2;
                    }else if(yangwoqizuo<17) {
                        yangwoqizuoAssess=3;
                    }else if(yangwoqizuo<24) {
                        yangwoqizuoAssess=4;
                    }else{
                        yangwoqizuoAssess=5;
                    }

                }else {
                    if(yangwoqizuo<2) {
                        yangwoqizuoAssess=1;
                    }else if(yangwoqizuo<7) {
                        yangwoqizuoAssess=2;
                    }else if(yangwoqizuo<14) {
                        yangwoqizuoAssess=3;
                    }else if(yangwoqizuo<21) {
                        yangwoqizuoAssess=4;
                    }else{
                        yangwoqizuoAssess=5;
                    }
                }
            }else {
                if (sex.equals("男")){
                    if(fuwocheng<4) {
                        fuwochengAssess= 1;
                    }else if(fuwocheng<8) {
                        fuwochengAssess=2;
                    }else if(fuwocheng<17) {
                        fuwochengAssess=3;
                    }else if(fuwocheng<24) {
                        fuwochengAssess=4;
                    }else{
                        fuwochengAssess=5;
                    }
                }else {
                    if(fuwocheng<2) {
                        fuwochengAssess= 1;
                    }else if(fuwocheng<6) {
                        fuwochengAssess=2;
                    }else if(fuwocheng<14) {
                        fuwochengAssess=3;
                    }else if(fuwocheng<20) {
                        fuwochengAssess=4;
                    }else{
                        fuwochengAssess=5;
                    }
                }


                if (sex.equals("男")){
                    if(yangwoqizuo<2) {
                        yangwoqizuoAssess= 1;
                    }else if(yangwoqizuo<7) {
                        yangwoqizuoAssess=2;
                    }else if(yangwoqizuo<14) {
                        yangwoqizuoAssess=3;
                    }else if(yangwoqizuo<21) {
                        yangwoqizuoAssess=4;
                    }else{
                        yangwoqizuoAssess=5;
                    }

                }else {
                    if(yangwoqizuo<2) {
                        yangwoqizuoAssess=1;
                    }else if(yangwoqizuo<5) {
                        yangwoqizuoAssess=2;
                    }else if(yangwoqizuo<12) {
                        yangwoqizuoAssess=3;
                    }else if(yangwoqizuo<19) {
                        yangwoqizuoAssess=4;
                    }else{
                        yangwoqizuoAssess=5;
                    }
                }

            }

        return (fuwochengAssess+yangwoqizuoAssess)/2;
    }
//   身高、胸围、体重指数
    public int getPhysique(float height,float weight,float chest){
        float assessmentNum=height-(weight+chest);
        if (assessmentNum<10){
            return 5;
        }else if (assessmentNum<20){
            return 4;
        }else if (assessmentNum<27){
            return 3;
        }else if(assessmentNum<35){
            return 2;
        }else {
            return 1;
        }
    }

    public int getWithinFat(String sex,float userWaist,float userHipline){
        float assessmentNum=userWaist/userHipline;
        if (sex.equals("男")){
            if (assessmentNum<0.85){
                return 0;
            }else if (assessmentNum<0.95){
                return 1;
            }else {
                return 2;
            }
        }else{
            if (assessmentNum<0.75){
                return 0;
            }else if (assessmentNum<0.85){
                return 1;
            }else {
                return 2;
            }
        }

    }

    public String getQualityNumber(float height,float weight){
        float assessmentNum=weight/(height*height);
        if (assessmentNum<17.5){
            return "消瘦";
        }else if (assessmentNum<24){
            return "正常";
        }else if (assessmentNum<28){
            return "超重";
        }else{
            return "肥胖";
        }
    }

    public int  getSubhealthyNumber(Subhealthy s){
        int assessmentNum=s.getQ1()+s.getQ2()+s.getQ3()+s.getQ4()+s.getQ5()+s.getQ6()+s.getQ7()+s.getQ8()+s.getQ9()+s.getQ10()+s.getQ11()+s.getQ12()+s.getQ13()+s.getQ14()+s.getQ15()+s.getQ16()+s.getQ17()+s.getQ18()+s.getQ19()+s.getQ20()+s.getQ21()+s.getQ22()+s.getQ23()+s.getQ24()+s.getQ25()+s.getQ26();
        return assessmentNum;
    }
}
