
import java.util.*;

class btree
{
    public static class Node
    {
        int data;
        Node left;
        Node right;

        Node(int d,Node l,Node r)
        {
            data=d;
            left=l;
            right=r;
        }
    }
    public static class Pair
    {
        Node node;
        int state;

        Pair(Node node,int state)
        {
            this.node=node;
            this.state=state;
        }
    }

    
    public static void display(Node node)
    {
        if(node==null)
        return;
        String str="";

        str+=node.left==null?".":node.left.data+"" ;
        str+=" <- "+node.data+"  -> ";
        str+=node.right==null?".":node.right.data+"" ;
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {
        if(node==null)
        return 0;

        int ls=size(node.left);
        int rs=size(node.right);
        int ts=ls+rs+1;
        return ts;

    }

    public static int sum(Node node) {
        if(node==null)
        {
            return 0;
        }
        int l=sum(node.left);
        int r=sum(node.right);
        int tsm=l+r+node.data;
        return tsm;
    }

    public static int max(Node node) {

        if(node==null)
        {
            return Integer.MIN_VALUE;
        }

        int lm=max(node.left);
        int rm=max(node.right);
        int tm=Math.max(node.data,Math.max(lm,rm));
        return tm;
    }

    public static int height(Node node) {

        if(node==null)
        {
            return -1; //-1 for edges  0 for nodes
        }
        int lh=height(node.left);
        int rh=height(node.right);
        int th=Math.max(lh,rh)+1;

        return th;
    }

    public static void traversal(Node node) {
    if(node ==null)
    {
        return;
    }        
        System.out.println(node.data+ "preorder");
        traversal(node.left);
        System.out.println(node.data+ "inorder");
        traversal(node.right);
        System.out.println(node.data+ "postorder");


    }



    public static void levelOrder(Node node)
    {
        Queue<Node> mq=new ArrayDeque<>();
        mq.add(node);

        while(mq.size()>0)
        {
            int count=mq.size();
            for(int i=0;i<count;i++)
            {
                node=mq.remove();
                System.out.print(node.data+" ");
                if(node.left!=null)
                {
                    mq.add(node.left);
                }
                if(node.right!=null)
                {
                    mq.add(node.right);
                }

            }
            System.out.println();
        }
    }
  

public static void ItrativeTraversal(Node node) {
    Stack<Pair> st=new Stack<>();
    Pair rtp=new Pair(node,1);
    st.push(rtp);

    String pre="";
    String post="";
    String in="";

    while(st.size()>0)
    {
        Pair top=st.peek();
        if(top.state==1)
        {
            pre+=top.node.data+" ";
            top.state++;
            if(top.node.left!=null)
            {
                Pair lp=new Pair(top.node.left,1);
                st.push(lp);
            }
        }
        else if(top.state==2)
        {
            in+=top.node.data+" ";
            top.state++;
            if(top.node.right!=null)
            {
                Pair rp=new Pair(top.node.right,1);
                st.push(rp);
            }
        }
        else 
        {
            post+=top.node.data+" ";
            st.pop();
        }
    }

    System.out.println(pre);
    System.out.println(in);
    System.out.println(post);
    
}

static ArrayList<Integer> path;
    public static boolean find(Node node,int data) {
        if(node==null)
        return false;
        
        if(node.data==data)
        {
            path.add(node.data);
        return true;
        }

        boolean fil=find(node.left,data);
        if(fil)
        {
            path.add(node.data);

        return true;
        }
boolean fir=find(node.right,data);
        if(fir)
        {
            path.add(node.data);

        return true;
        }
return false;
    }
    public static void kLevelDown(Node node,int k)
    {

        if(node==null || k<0)
        {
            return;
        }
        if(k==0)
        {
            System.out.print(node.data+" ");
        }


        kLevelDown(node.left, k-1);
        kLevelDown(node.right, k-1);
        
    }
        public static void pathToLeafFromNodeInRange(Node node,String path,int sum,int hi,int lo)
        {
            if(node==null)
            return;

            if(node.left==null &&node.right==null)
            {
                sum+=node.data;
                if(sum>=lo&&sum<=hi)
                {
                    System.out.println(path+node.data);
                }
            }
            pathToLeafFromNodeInRange(node.left, path+node.data+" ", sum+node.data,hi, lo);
            pathToLeafFromNodeInRange(node.right, path+node.data+" ", sum+node.data,hi, lo);

        }

        public static Node transformLeftCloneTree(Node node) {

            if(node==null)
            {
                return null;
            }
            

            Node lc=transformLeftCloneTree(node.left);
            Node rc=transformLeftCloneTree(node.right);

            Node nn=new Node(node.data,lc,null);
            node.left=nn;
            node.right=rc;
            return node;
        }
        public static Node transformbackfromLeftCloneTree(Node node)
        {
            if(node==null)
            {
                return null;
            }

            Node l=transformbackfromLeftCloneTree(node.left.left);
            Node r=transformbackfromLeftCloneTree(node.right);

            node.left=l;
            node.right=r;

            return node;
        }
        public static void SingleChild(Node node,Node parent)
        {
            if(node==null)
            {
                return;
            }
                if(parent!=null&&parent.left!=null&&parent.right==null)
                {
                    System.out.println(node.data);
                }
                else if(parent!=null&&parent.left==null&&parent.right!=null)
                {
                    System.out.println(node.data);
                }

            SingleChild(node.left, node);
            SingleChild(node.right, node);
        }


        public static Node removeLeaf(Node node)
        {
            if(node==null)
        {
            return null;
        }

        if(node.left==null && node.right==null)
    {
        return null;
    }
          node.left=removeLeaf(node.left);
        node.right=removeLeaf(node.right);
return node;

        }
        public static int diameter(Node node)
        {
            id(node==null)
            {
                return 0;
            }
        int ld=diameter(node.left);
        int rd=diameter(node.right);

        int f=height(node.left)+height(node.right)+2;
        int dia=Math.max(f,Math.max(ld,rd));
        return dia;

        }
        static int tilt=0;

        public static int tilt(Node node) {
            if(node==null)
            {
                return 0;
            }
            
            int ls=tilt(node.left);
            int rs=tilt(node.right);

            int ltilt+=Math.abs(ls=rs);

            tilt+=ltilt;
            int ts=ls+rs+node.data;
            return ts;

        }
        public static class BSTPair
        {
            boolean isBST;
            int min;
            int max;
    
        }
    
        public static BSTPair isBST(Node node)
        {
            if(node==null)
            {
                BSTPair bp=new BSTPair();
                bp.min=Integer.MAX_VALUE;
                bp.max=Intger.MIN_VALUE;
                bp.isBST=true;
                return bp;
            }
    
            BSTPair lp=isBST(node.left); 
            BSTPair rp=isBST(node.right);
    
            BSTPair mp=new BSTPair();
    
            mp.isBST=lp.isBST&&rp.isBST&&(node.data>=lp.max&&node.data<=rp.min);
            mp.max=Math.max(node.data,Math.max(lp.max,rp.max));
            mp.min=Math.min(node.data,Math.min(lp.min,rp.min));
    return mp;
    
        }
    
    public static void main(String args[])
    {
        Integer[] arr={50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
//--------------------------------------------------------------------------------
//construct
        Node root=new Node(arr[0],null,null);
        Pair rtp=new Pair(root,1);

        Stack<Pair> st=new Stack<>();
        int idx=0;
        st.push(rtp);
        while(st.size()>0)
        {
            Pair top=st.peek();

            if(top.state==1)
            {
                idx++;
                if(arr[idx]!=null)
                {
                top.node.left=new Node(arr[idx],null,null);
                Pair lp=new Pair(top.node.left,1);
                st.push(lp);
                }
                else
                {
                    top.node.left=null;
                }
                top.state++;
            }
            else if(top.state==2)
            {
                idx++;
                if(arr[idx]!=null)
                {
                top.node.right=new Node(arr[idx],null,null);
                Pair rp=new Pair(top.node.right,1);
                st.push(rp);
                }
                else
                {
                    top.node.right=null;
                }
                top.state++;
            }
            else
            {
                st.pop();
            }
        }

      
       //display(root);
        // System.out.println(max(root));
        // System.out.println(height(root));
        // System.out.println(sum(root));
        // System.out.println(size(root));
        // traversal(root);
        //levelOrder(root);
        //ItrativeTraversal(root);
        //path=new ArrayList<>();
        // System.out.println(find(root,70));//node to root  path
        // System.out.println(path);
        //kLevelDown(root,2);
        // pathToLeafFromNodeInRange(root, "",0, 100, 20);
        // transformLeftCloneTree(root);
        // transformbackfromLeftCloneTree(root);
        //SingleChild(root,root);
        //System.out.println(tilt(root));
        //removeLeaf(root);
        //tilt(root);
        // BSTPair bp=isBST(root);
        // System.out.pritnln(bp);
    
    }
}