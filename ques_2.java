class Solution {
    public long numOfSubsequences(String s) {
        int n = s.length();
        long countL = 0;
        long countLC = 0;
        long countLCT = 0;
        for(char c:s.toCharArray()){
            if(c=='L') countL++;
            else if(c=='C') countLC+=countL;
            else if(c=='T') countLCT+=countLC;
        }
        long[] suffixT = new long[n+1];
        long[] suffixCT = new long[n+1];
        for(int i=n-1;i>=0;i--){
            char ch = s.charAt(i);
            suffixT[i]=suffixT[i+1];
            suffixCT[i]=suffixCT[i+1];
            if(ch=='T') suffixT[i]++;
            if(ch=='C') suffixCT[i]+=suffixT[i];
        }
        long extra = 0;
        long prefixL=0;
        long prefixLC=0;
        for(int i=0;i<=n;i++){
            // insert L
            extra = Math.max(extra,suffixCT[i]);
            // insert C
            extra = Math.max(extra,prefixL*suffixT[i]);
            //insert T
            extra = Math.max(extra,prefixLC);
            if(i<n){
                char ch = s.charAt(i);
                if(ch=='L') prefixL++;
                if(ch=='C') prefixLC+=prefixL;
            }
        }
        return countLCT+extra;
    }
}
