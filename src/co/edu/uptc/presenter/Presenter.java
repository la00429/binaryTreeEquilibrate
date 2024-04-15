package co.edu.uptc.presenter;

import co.edu.uptc.model.Student;
import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.structures.BinaryTreeEquilibrate;

import java.util.Comparator;

public class Presenter {


        public static void main(String[] args) {
            BinaryTreeEquilibrate<Integer> binaryTree = new BinaryTreeEquilibrate<Integer>(Comparator.naturalOrder());

            binaryTree.insert(29);
            binaryTree.insert(19);
            binaryTree.insert(50);
            binaryTree.insert(12);
            binaryTree.insert(22);
            binaryTree.insert(60);
            binaryTree.insert(8);
            binaryTree.insert(17);
            binaryTree.insert(5);


            System.out.println("Search 20:" + binaryTree.searchData(29));

            try {
                binaryTree.remove(20);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println("PreOrder:" + binaryTree.preOrder());
            System.out.println("InOrder:" + binaryTree.inOrder());
            System.out.println("PostOrder:" + binaryTree.postOrder());

            BinaryTree<Student> binaryTreeStudent = new BinaryTree<Student>(Comparator.comparing(Student::getCode));
            binaryTreeStudent.insert(new Student("Juan", "Perez", 11));
            binaryTreeStudent.insert(new Student("Ana", "Garcia", 48));
            binaryTreeStudent.insert(new Student("Pedro", "Gomez", 21));
            binaryTreeStudent.insert(new Student("Maria", "Lopez", 35));
            binaryTreeStudent.insert(new Student("Carlos", "Gonzalez", 53));
            binaryTreeStudent.insert(new Student("Luis", "Rodriguez", 63));
            binaryTreeStudent.insert(new Student("Sofia", "Martinez", 74));

            System.out.println("InOrder:" + binaryTreeStudent.inOrder());
            System.out.println("PreOrder:" + binaryTreeStudent.preOrder());
            System.out.println("PostOrder:" + binaryTreeStudent.postOrder());

            System.out.println("Search code student 2:" + binaryTreeStudent.searchData(new Student("", "", 21)));
        }

}
