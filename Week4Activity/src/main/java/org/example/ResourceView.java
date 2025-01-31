package org.example;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ResourceView extends JFrame {
    ResourceView rv;

    public ResourceView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Resource View");
        this.setSize(400, 300);
        rv = this;



        JPanel treePanel = new JPanel();
        treePanel.setLayout(new BorderLayout());

        //create nodes for tree -- sample code
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
//        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
//        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");
//        DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("Child 3");
//        root.add(child1);
//        root.add(child2);
//        root.add(child3);
//

//j tree view for seeing directories. diff betw leaf and child.  child is folder leaf is file.  

//        DefaultMutableTreeNode subchild11 = new DefaultMutableTreeNode("Child of 1.1");
//        DefaultMutableTreeNode subchild12 = new DefaultMutableTreeNode("Child of 1.2");
//        child1.add(subchild11);
//        child1.add(subchild12);
//
//
        // create JTree

        DefaultMutableTreeNode root = buildResourceTree("");
        JTree tree = new JTree(root);
        JScrollPane treeScrollPane = new JScrollPane(tree); //its a big tree so this will be helpful.  
        treePanel.add(treeScrollPane, BorderLayout.CENTER);
        this.add(treePanel, BorderLayout.CENTER);
        // I can't test my code at the moment because Java has conflicting versions on my computer, I'm unsure how to proceed.  I would have brought this problem up sooner if I ran into it closer to the beginnning of the course, but the first few files ran just fine.  

    }

    public DefaultMutableTreeNode buildResourceTree(String folderName) {
        // TODO: get resource folder URL for given folderName
//hold ctrl and click a function to find def:
//class loader,  it knows where things are stored.  
ClassLoader classLoader = this.getClass().getClassLoader();
URL resource = classLoader.getResource(folderName);

       if (resource == null) {
           throw new IllegalArgumentException("Folder not found: " + folderName);
       }//if folder isn't found

        // TODO: replace this wih variable declaration only -- see commented line
 File folder;
       // File folder = new File(folderName);
       try {
           folder = new File(resource.toURI());
       } catch (Exception e) {
           throw new RuntimeException("Error accessing folder: " + folderName, e);
       }
//
       if (!folder.exists() || !folder.isDirectory()) {
           throw new IllegalArgumentException("Invalid folder: " + folderName);
       }
//checking if its the right type of folder, or exists.  
        return buildTreeNode(folder);
    }

    private DefaultMutableTreeNode buildTreeNode(File folder) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(folder.getName());
        // TODO: get all folder and files recursively
        File[] files = folder.listFiles();
        if(files != null){
            for(File file : files){
                if(file.isDirectory()){
                    node.add(buildTreeNode(file));  
                }else{
                    node.add(new DefaultMutableTreeNode(file.getName()));
                }
            }
        }

        return node;
    }


    public static void main(String[] args) {
        ResourceView resourceView = new ResourceView();
        resourceView.setVisible(true);
    }
}