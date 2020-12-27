package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import admin.admin_login;
//import main.main;

import DB.DataBase;


public class MenuPage extends JPanel {
	// 결제된 정보가 담겨 있는 어레이리스트들(중복값이 있음)
	public static ArrayList<String> ProductName = new ArrayList<String>(); //중복값이 있는 상품이름 리스트
	public static ArrayList<Integer> Price = new ArrayList<Integer>(); //중값이 있는 상품가격 리스트
   
	// 실제 AdminPage_Panel로 넘겨주는 어레이리스트들(중복값이 없음)
	public static ArrayList<String> ProductList = new ArrayList<String>(); //중복값이 없는 상품이름 리스트
	public static ArrayList<Integer> PriceList = new ArrayList<Integer>(); //중복값이 없는 상품가격 리스트
	public static ArrayList<Integer> CntList = new ArrayList<Integer>(); //선택된 상품갯수 리스트
   
	// 위의 중복값이 있는 어레이리스트들을 중복값 없는 데이터로 만들어주는 기능
	public class operation {
		operation(){		
			for(int i = 0; i <ProductName.size(); i=i+1) {
				System.out.println(ProductName.get(i));
				if(!ProductList.contains(ProductName.get(i))) {
					ProductList.add(ProductName.get(i));
					PriceList.add(Price.get(i));
					int cnt = 0;
					for(int I = i; I < ProductName.size(); I = I+1) {
						if(ProductName.get(i) == ProductName.get(I)) {
							cnt = cnt + 1;
						}
					}
					CntList.add(cnt);
				}
				else {
					continue;
				}
			}
			System.out.println(ProductList);
			System.out.println(PriceList);
			System.out.println(CntList);
			
			new DataBase();
		}		
	}
	
 //--------------------------------- 배열로 생성한 버튼 정보 -----------------------------------------
   JTextField textfield = new JTextField(30);
   JButton[] MenuPagebtn = new JButton[20];
   
   String[] menu = { "오뚜기)희망줄라면컵","농심)신라면블랙김치컵","HEYROO제주마늘라면컵","삼양)맛있는라면컵","농심)신라면건면사발",
		   			"씨믹스)핑크퐁상어가족","미성)LoL에그빈","네슬레)킷캣팝스","해태)얼초크리스마스파티","롯데)가나밀크티맛",
		   			"HEYROO파르페초코볼","하겐)쿠키앤크림미니컵","하겐)로스티드라떼파인트","롯데)월드콘쿠앤크","서주)쑥떡쑥떡샌드",
		   			"OKF)골드유자500ml","빙)요플레토핑화이트초코","와일드베리)링곤베리350ml","돈시몬)착즙오렌지200ml","다논)요거톡스타볼"};
   
   int[] price = {1000,1600,1500,1500,1500,
              	2000,2000,1500,5000,3000,
              	2500,4800,12900,1800,1800,
              	2000,1500,1500,1500,1400};
   String[]img = {
   };
   
//----------------------------------- 텍스트 필드에 들어갈 정보 ---------------------------------------  
   String[] ColName = {"메뉴", "수량", "가격"};
   Color[] color = {new Color(225,128,0), new Color(225,128,0), new Color(225,128,0)};
   String[][] Data;
   int count = 1;
   
   
   
   
//-------------------------------------메뉴 페이지 설정하기-------------------------------------------
   public MenuPage() {
      
      MenuButton menubtn = new MenuButton();
      ScrollPane scrollpane = new ScrollPane();
      
      setLayout(null); 
      setBackground(Color.LIGHT_GRAY); //판넬 색상 설정
      textfield.setBackground(Color.LIGHT_GRAY); //텍스트 필드 색상 설정
 
      textfield.setBounds(510, 740, 500, 200); //텍스트 필드 크기 조정
      add(textfield);
 
      scrollpane.setBounds(10, 740, 500, 200); //스크롤 크기 조정
      add(scrollpane);
 
      menubtn.setSize(1520, 700); //메뉴 버튼 생성 범위 지정
      menubtn.setLocation(30, 20); //메뉴 버튼 생성 범위 지정
      add(menubtn);
      
      //----------------------------------결제 하기 버튼-------------------------------------------
      JButton PayButton = new JButton("결제하기");
      PayButton.setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\결제하기.jpg"));
      PayButton.setBounds(1370, 740, 180, 150);
      add(PayButton);
      PayButton.addActionListener(new ActionListener() {
      
        @Override
        public void actionPerformed(ActionEvent e) {
           JButton MenuPagebtn = (JButton)e.getSource();
           int rowCont = table.getRowCount();
           int sum = 0;
           for(int i=0; i<rowCont; i=i+1) {
              sum = sum + (int)table.getValueAt(i, 2);
           }
           textfield.setText(String.valueOf(" 총금액 : " + sum +"원"));
           textfield.setFont(new Font("맑은 고딕", Font.BOLD, 40));
           
           new operation();
         }
      });

     
      //----------------------------------선택 취소 버튼-------------------------------------------
      JButton RemoveButton = new JButton("선택 취소");
      RemoveButton.setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\선택취소.jpg"));
      RemoveButton.setBounds(1200, 740, 150, 150);
      add(RemoveButton);
      
      RemoveButton.addActionListener(new ActionListener() {
    
          @Override
          public void actionPerformed(ActionEvent e) {
             JButton MenuPagebtn = (JButton)e.getSource();
             DefaultTableModel m = (DefaultTableModel)table.getModel();            
             m.removeRow(table.getSelectedRow());         
          }
       });
         
      
      //----------------------------------전체 취소 버튼-------------------------------------------
      JButton AllRemoveButton = new JButton("전체 취소");
      AllRemoveButton.setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\전체취소.jpg"));
      AllRemoveButton.setBounds(1030, 740, 150, 150);
      add(AllRemoveButton);
      

      AllRemoveButton.addActionListener(new ActionListener() {
       
          @Override
          public void actionPerformed(ActionEvent e) {
             JButton MenuPagebtn = (JButton)e.getSource();
             DefaultTableModel m = (DefaultTableModel)table.getModel();
             m.setRowCount(0);
             textfield.setText(String.valueOf(""));
          }         
       });
      
   }




DefaultTableModel model = new DefaultTableModel(Data, ColName);
   JTable table = new JTable(model);


//--------------------------------사용자가 선택한 메뉴를 담는 리스트------------------------------------
   class ScrollPane extends JPanel{
      
      ScrollPane() {
         
            DefaultTableModel m = (DefaultTableModel)table.getModel();
            
            setBackground(Color.LIGHT_GRAY);
            table.setLayout(new GridLayout(350,30,200,10));
            table.setRowHeight(50);
            table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 15));
            add(new JScrollPane(table));
         }
      }
   
   
      
   
//------------------------------------------- 메뉴 버튼 20개 ---------------------------------------
   class MenuButton extends JPanel {
      
      MenuButton() {
         
          	setLayout(new GridLayout(4,10,10,5));
            setBackground(Color.LIGHT_GRAY);

              for(int i=0; i<MenuPagebtn.length;i=i+1) {
                 MenuPagebtn[i] = new JButton(menu[i]);
                 add(MenuPagebtn[i]);
              }
            	  MenuPagebtn[0].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\희망줄라면.jpg"));
            	  add(MenuPagebtn[0]);
            	  
            	  MenuPagebtn[1].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\블랙김치.jpg"));
            	  add(MenuPagebtn[1]);
            	  
            	  MenuPagebtn[2].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\제주마늘라면.jpg"));
            	  add(MenuPagebtn[3]);
            	  
            	  MenuPagebtn[3].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\맛있는라면.jpg"));
            	  add(MenuPagebtn[3]);
            	  
            	  MenuPagebtn[4].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\건면사발.jpg"));
            	  add(MenuPagebtn[4]);
            	  
            	  MenuPagebtn[5].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\핑크퐁상어가족.jpg"));
            	  add(MenuPagebtn[5]);
            	  
            	  MenuPagebtn[6].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\LOL에그빈.jpg"));
            	  add(MenuPagebtn[6]);
            	  
            	  MenuPagebtn[7].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\킷캣팝스.jpg"));
            	  add(MenuPagebtn[7]);
            	  
            	  MenuPagebtn[8].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\얼초크리스마스파티.jpg"));
            	  add(MenuPagebtn[8]);
            	  
            	  MenuPagebtn[9].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\가나밀크티맛.jpg"));
            	  add(MenuPagebtn[9]);
            	  
            	  MenuPagebtn[10].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\파르페초코볼.jpg"));
            	  add(MenuPagebtn[10]);
            	  
            	  MenuPagebtn[11].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\쿠키앤크림미니컵.jpg"));
            	  add(MenuPagebtn[11]);
            	  
            	  MenuPagebtn[12].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\로스티드라떼파인트.jpg"));
            	  add(MenuPagebtn[12]);
            	  
            	  MenuPagebtn[13].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\월드콘쿠앤크.jpg"));
            	  add(MenuPagebtn[13]);
            	  
            	  MenuPagebtn[14].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\쑥떡쑥떡샌드.jpg"));
            	  add(MenuPagebtn[14]);
            	  
            	  MenuPagebtn[15].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\골드유자500ml.jpg"));
            	  add(MenuPagebtn[15]);
            	  
            	  MenuPagebtn[16].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\요플레토핑화이트초코.jpg"));
            	  add(MenuPagebtn[16]);
            	  
            	  MenuPagebtn[17].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\링곤베리350ml.jpg"));
            	  add(MenuPagebtn[17]);
            	  
            	  MenuPagebtn[18].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\착즙오렌지200ml.jpg"));
            	  add(MenuPagebtn[18]);
            	  
            	  MenuPagebtn[19].setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\menu\\요거톡스타볼.jpg"));
            	  add(MenuPagebtn[19]);
            	  

            	  
   
    
//-------------------------------- 메뉴 입력 하고 하단 텍스트 필드에 담는 반복문 ---------------------------
            	  
            	  
    for(int i=0; i<MenuPagebtn.length;i=i+1) {
       
        final int index = i;
        
        MenuPagebtn[i].addActionListener(new ActionListener() {
           
           @Override
           public void actionPerformed(ActionEvent e) {
            
              JButton MenuPagebtn = (JButton)e.getSource();
              DefaultTableModel m = (DefaultTableModel)table.getModel();
              m.addRow(new Object[]{menu[index],count,price[index]});
              
              
              for(int i=0; i<ProductName.size();i=i+1) {
                  ProductName.remove(i); //어레이리스트 비우기(초기화)
                  Price.remove(i); //어레이리스트 비우기(초기화)
               }
               for(int i=0; i<ProductList.size();i=i+1) {
                  ProductList.remove(i); //어레이리스트 비우기(초기화)
                  PriceList.remove(i); //어레이리스트 비우기(초기화)
                  CntList.remove(i); //어레이리스트 비우기(초기화)                 
               }
              
              
              ProductName.add(menu[index]);
              Price.add(price[index]);
                    }
              });
          }
    
    
    
//    //쿠폰
//    PaymentBtn[0].addActionListener(new ActionListener() {
//       
//          @Override
//          public void actionPerformed(ActionEvent e) {
//             JButton MenuPagebtn = (JButton)e.getSource();
//             table.setValueAt(0, table.getSelectedRow(), 2);            
//          }

      }

	}
  }
