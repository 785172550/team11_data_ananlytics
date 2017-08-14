/**
 * Created by Administrator on 2017/8/9.
 */

function calculateMApos(dayCount, pos, data) {
    var res;
    for (var i = pos - dayCount; i < pos; i++) {
        res += data.values[i][1];
    }
    return res / dayCount;

}

function calculateEMA(data) {
    var EMA1 = [];
    var EMA2 = [];
    var a1 = 2.0 / 13;
    var a2 = 2.0 / 27;
    EMA1.push(data.values[0][1]);
    EMA2.push(data.values[0][1]);
    for (var i = 1, len = data.values.length; i < len; i++) {
        EMA1.push(a1*(data.values[i][1]-EMA1[i-1])+EMA1[i-1]);
        EMA2.push(a2*(data.values[i][1]-EMA2[i-1])+EMA2[i-1]);
    }
    return {
        EMA1: EMA1,
        EMA2: EMA2
    };
}

function calculateDIF(data, EMA) {
    var DIF = [];
    for (var i = 0, len = data.values.length; i < len; i++) {
        DIF.push(EMA.EMA1[i]-EMA.EMA2[i]);
    }
    return DIF;
}

function calculateDEA(DIF) {
    var DEA = [];
    DEA.push(DIF[0]);
    for (var i = 1, len = DIF.length; i < len; i++) {
        DEA.push(0.8 * DEA[i-1] + 0.2 * DIF[i]);
    }
    return DEA;
}

function calculateBAR(DIF, DEA) {
    var BAR = [];
    for (var i = 0, len = DIF.length; i < len; i++) {
        BAR.push(2 * (DIF[i] - DEA[i]).toFixed(2));
    }
    return DEA;

}



function calculateMD(dayCount,MA,data) {
    var MD = [];
    for(var i=0, len=data.values.length;i<len;i++){
        if (i < dayCount) {
            MD.push('-');
            continue;
        }
        var sum=0;
        for(var j=1;j<dayCount;j++){
            var tmp = MA[i] - data.values[i-j][1];
            sum += Math.pow(tmp,2);
        }
        sum = Math.sqrt(sum/dayCount);
        MD.push(sum.toFixed(3));
    }
    return MD;
}

function calculateBOLL(dayCount,MD,data){
    var MB = calculateMA(dayCount-1,data);
    var UP = [];
    var DN = [];
    for(var i = 0,len=MD.length;i<len;i++){
        if (i < dayCount) {
            UP.push('-');
            DN.push('-');
            continue;
        }
        UP.push(MB[i]+2*MD[i]);
        DN.push(MB[i]-2*MD[i]);
    }
    return {
        MB: MB,
        UP: UP,
        DN: DN
    };
}