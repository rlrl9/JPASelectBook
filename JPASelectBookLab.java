package jpamvcexam.mainview;

import jpamvcexam.model.dto.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class JPASelectBookLab {
    public void close(){

    }
    public static void main(String[] args)  {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("emptest");
        EntityManager em = factory.createEntityManager();
        Scanner sc=new Scanner(System.in);
        int num;
        TypedQuery<Book> q;
        List<Book> resultList;
        while (true){
            System.out.println("1.모두 출력하기\n2.가격이 높은 순으로 출력하기\n3.20000원 이상의 도서들만 출력하기\n4.id가 3번인 도서 출력하기\n5.도서명에 '자바'또는 '스프링'을 포함하는 도서들만 출력하기\n6.분류별 도서 가격의 합을 출력하기\n7.종료\n\n원하는 메뉴의 번호를 선택:");
            num=sc.nextInt();
            if(num==1){
                q = em.createQuery("SELECT t FROM Book t", Book.class);
                resultList = q.getResultList();
                for(Book vo : resultList)
                    System.out.println(vo);
            }
            else if(num==2){
                q = em.createQuery("SELECT t FROM Book t order by t.price desc", Book.class);
                resultList = q.getResultList();
                for(Book vo : resultList)
                    System.out.println(vo);
            }
            else if(num==3){
                q = em.createQuery("SELECT t FROM Book t where t.price>20000", Book.class);
                resultList = q.getResultList();
                for(Book vo : resultList)
                    System.out.println(vo);
            }
            else if(num==4){
                q = em.createQuery("SELECT t FROM Book t where t.id=3", Book.class);
                resultList = q.getResultList();
                for(Book vo : resultList)
                    System.out.println(vo);
            }
            else if(num==5){
                q = em.createQuery("SELECT t FROM Book t where t.title like '%자바%' or t.title like '%스프링%'", Book.class);
                resultList = q.getResultList();
                for(Book vo : resultList)
                    System.out.println(vo);
            }
            else if(num==6){
                Query qi = em.createQuery("SELECT t.kind, sum(t.price) FROM Book t group by t.kind");
                List<Object[]> resultLists = qi.getResultList();
                for(Object vo : resultLists){
                    Object[] o=(Object[]) vo;
                    String kind=(String) o[0];
                    Long price=(Long) o[1];
                    System.out.println("분류 코드 "+kind+" "+price);
                }
            }else if(num==7){
                break;
            }
        }

    }
}
