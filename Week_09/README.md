学习笔记

一、根据https://leetcode-cn.com/problems/unique-paths-ii/solution/jian-dan-dpbi-xu-miao-dong-by-sweetiee/
不同路径 2 这道题目的状态转移方程可写为

dp[i][j] = dp[i-1,j]+dp[i,j-1]   当(i,j)上无障碍物
dp[i][j] = 0					 当(i,j)上有障碍物

二、力扣题8字符串转换整数 (atoi)
https://leetcode-cn.com/problems/string-to-integer-atoi/

//Java
public int myAtoi(String str){
	int index = 0,sign = 1, total=0;
	//1. Empty string
	if(str.length()==0) return 0;
	
	//2. Remove Spaces
	while(str.charAt(index) == ' ' && index < str.length())
		index++;
		
	//3. Handle signs
	if(str.charAt(index)=='+' || str.charAt(index)=='-'){
		sign = str.charAt(index) == '+'? 1 : -1;
		index++;
	}
	
	//4. Convert number and avoid overflow
	while(index<str.length()){
		int digit = str.charAt(index) - '0';
		if(digit < 0 ||  digit > 9) break;
		
		//check if total will be overflow after 10 times and add digit
		if(Integer.MAX_VALUE/10 < total ||
			Integer.MAX_VALUE/10 ==  total && Integer.MAX_VALUE%10 <digit)
			return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			
		total = 10*total + digit;
		index++;
	}
	return total*sign;
}


# python
class Solution(object):

	def myAtoi(self,s):
	
		if len(s) == 0:return 0
		ls =  list(s.strip())
		
		sign = -1 if ls[0] == '-'  else 1

		if ls[0] in ['-','+']:del ls[0]
		
		ret, i = 0,0
		
		while i<len(ls) and ls[i].isdigit():
			ret = ret*10 + ord(ls[i]) - ord('0')
			i +=  1
			
		return max(-2**31, min(sign*ret, 2**31-1))

		
//C
int myAtoi(String str){
	int res = 0;
	int sign = 1;
	size_t index = 0;
	if(str.find_first_not_of(' ') != string::npos)
		index = str.find_first_not_of(' ');
	if(str[index] == '+' || str[index] == '-')
		sign = str[index] == '-' ? -1 : 1;
		
	while(index < str.size() && isdigit(str[index])){
		res = res*10 + (str[index++] - '0');
		if(res*sign > INT_MAX) return INT_MAX;
		if(res*sign < INT_MIN) return INT_MIN;
	}
	
	return res*sign;
}


//C++
class Solution{
public:
	int myAtoi(String str){
		anto i = getStartIndexOfNonSpace(str);
		if(i<0) return 0;
		
		auto sign = handleSign(str,i);
		auto num = sign * convertStringToLong(str,i);
		return convertLongToInt(num);
	}
	
private:
	int getStartIndexOfNonSpace(const string &str){
		for(int i=0;i<str.size();++i){
			if(str[i] != ' ') return i;
		}
		
		return -1;
	}
	
	int handleSign(const string &str, int &i){
		if(str[i] == '-'){
			++i;
			return -1;
		}
		
		if(str[i]=='+') ++i;
		return 1;
		
	long convertStringToLong(const string &str, int i){
		long num = 0;
		while(i<str.size()){
			if(num>INT_MAX||!isdigit(str[i])) break;
			num = num * 10 + str[i] - '0';
			++i;
		}
		
		return num;
	}
	
	int convertLongToInt(const long &num){
		if(num<INT_MIN) return INT_MIN;
		if(num>INT_MAX) return INT_MAX;
		return (int)num;
	}
};
