import java.util.*;

/**
* author: xuyafan
* contact: https://github.com/xuyafan
* description:
*/
public class LeetCode {


    /**
     * day1 2018.12.19 Problem: 1 , 7
     */
    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * <p>
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * <p>
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * Given a 32-bit signed integer, reverse digits of an integer.
     * Example 1:
     * Input: 123
     * Output:  321
     * <p>
     * Example 2:
     * Input: -123
     * Output: -321
     * <p>
     * Example 3:
     * Input: 120
     * Output: 21
     * Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range.
     * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     */

    public int reverse(int x) {


        List<Integer> list = new ArrayList<Integer>();
        if (x > Math.pow(2, 32) - 1 || x < -Math.pow(2, 32)) {
            return 0;
        }

        if (x > 0) {
            while (x != 0) {
                list.add(x % 10);
                x = x / 10;
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (Integer i : list) {
                stringBuffer.append(i);
            }
            return Integer.valueOf(stringBuffer.toString());

        }
        if (x < 0) {
            x = -x;
            return -reverse(x);
        }

        return 0;
    }

    public int reverse2(int x) {
        long res = 0;
        //求Integer回文的写法
        for (; x != 0; x /= 10)
            res = res * 10 + x % 10;
        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }

    /**
     * day2 2018.12.20 Problem: 9,13,14
     */

    /**
     * Determine whether an integer is a palindrome. Do this without extra space.
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int res = 0;

        for (; x != 0; x = x / 10) {
            res = res * 10 + x % 10;
        }
        if (res == x) {
            return true;
        } else {
            return false;
        }

        //这里错在x已经改变了，不能比较res和x
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }

        int res = 0;
        int copyX = x;

        for (; copyX != 0; copyX = copyX / 10) {
            res = res * 10 + copyX % 10;
        }
        if (res == x) {
            return true;
        } else {
            return false;
        }

        //这里错在x已经改变了，不能比较res和x
    }

    /**
     * Given a roman numeral, convert it to an integer.
     * <p>
     * Input is guaranteed to be within the range from 1 to 3999.
     */
    public int romanToInt(String s) {
        //不知道罗马数字就去查啊
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            char c = chars[i];
            if ((c == 'I' || c == 'X' || c == 'C') && map.get(c) < map.get(chars[i + 1])) {
                result += -map.get(c);
            } else {
                result += map.get(c);
            }
        }
        result += map.get(chars[chars.length - 1]);
        return result;
    }

    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * "abcdefg"
     * "abcdefghijk"
     * "abcdfghijk"
     * "abcef"
     * return "abc"
     */
    public String longestCommonPrefix(String[] strs) {

        //不要少了检查
        if (strs.length == 0) {
            return "";
        }
        //最短str
        String shortStr = strs[0];
        for (String str : strs) {
            if (str.length() < shortStr.length()) {
                shortStr = str;
            }
        }
        int end = 0;
        boolean isPrefix = true;
        while (isPrefix) {
            //这种写法可能会越界
            String subStr = shortStr.substring(0, end);
            for (String str : strs) {
                if (!str.substring(0, end).equals(subStr)) {
                    isPrefix = false;
                }
            }
            end++;
        }
        return shortStr.substring(0, end - 1);
    }

    public String longestCommonPrefix2(String[] strs) {
        int len = strs.length;
        if (len == 0) return "";
        int minLen = 0x7fffffff;
        for (String str : strs) minLen = Math.min(minLen, str.length());
        for (int j = 0; j < minLen; ++j)
            for (int i = 1; i < len; ++i)
                if (strs[0].charAt(j) != strs[i].charAt(j))
                    return strs[0].substring(0, j);
        return strs[0].substring(0, minLen);
    }


    /**
     * day3 2018.12.21 Problem: 20
     */

    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * <p>
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     */
    public boolean isValid1(String s) {
        if (s.length() <= 1) return false;
        if (s.length() % 2 != 0) return false;

        //use stack
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {

                /*这种写法我们没有考虑到 "){" 这种情况
                建议在做这种题的时候，写一个例子用代码模拟思考的过程
                但是要多些几个例子，特殊的例子，去处理一些特殊情况
                或者我们想想，stack.peek()有可能会EmptyStackException，就不要忘记去判断
                */

                //fix
                if (stack.empty()) {
                    return false;
                }
                //fix
                if (isPair(stack.peek(), s.charAt(i))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();


    }

    private boolean isPair(char a, char b) {
        if (a == '(' && b == ')' || a == ')' && b == '(') {
            return true;
        }
        if (a == '[' && b == ']' || a == ']' && b == '[') {
            return true;
        }
        if (a == '{' && b == '}' || a == '}' && b == '{') {
            return true;
        }
        return false;
    }

    /**
     * day4 2018.12.22 Problem: 21，26
     */

    /**
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     * Input: 1->2->4, 1->3->4
       Output: 1->1->2->3->4->4
     */


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // 题意是用一个新链表来合并两个已排序的链表，那我们只需要从头开始比较已排序的两个链表，
    // 新链表指针每次指向值小的节点，依次比较下去，
    // 最后，当其中一个链表到达了末尾，我们只需要把新链表指针指向另一个没有到末尾的链表此时的指针即可。

        ListNode temp =new ListNode(0);

        //fix
        ListNode tempHead =temp;
        //fix

        while (l1!=null &&l2!=null){

            if(l1.val<=l2.val){

                temp.next=l1;
                l1=l1.next;
            }else {
                temp.next=l2;
                l2=l2.next;
            }
            temp=temp.next;
        }

        if(l1==null){
            temp.next=l2;
        }else {
            temp.next=l1;
        }

        //temp移到了最后，需要设置head来保存头部
//        return temp;
        return tempHead.next;

    }

    /**
     * Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.

     Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

     Given nums = [1,1,2],

     Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
     It doesn't matter what you leave beyond the new length.
     */

    //wrong
    public int removeDuplicates1(int[] nums) {


        int length =nums.length;

        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[j]==nums[i]){

                    for(int k=j;k<length-1;k++){
                        nums[k]=nums[k+1];
                    }
                    //移动后就不考虑最后一项
                    length--;
                }
            }
        }

       return length;
    }

    /**
     *
     *https://leetcode.com/problems/remove-duplicates-from-sorted-array/solution/
     */
    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


}
