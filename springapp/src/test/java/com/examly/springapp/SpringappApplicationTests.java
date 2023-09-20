package com.examly.springapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



import org.springframework.http.MediaType;

//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;




@Test 
    public void testcontrollerfolder() { 
        String directoryPath = "src/main/java/com/examly/springapp/controller"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    


    @Test 
    public void testcontrollerfile() { 
        String filePath = "src/main/java/com/examly/springapp/controller/ChairController.java"; 
        // Replace with the path to your file 
        File file = new File(filePath); 
        assertTrue(file.exists() && file.isFile()); 
    }

	@Test 
    public void testModelFolder() { 
        String directoryPath = "src/main/java/com/examly/springapp/model"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
    @Test 
    public void testModelFile() { 
        String filePath = "src/main/java/com/examly/springapp/model/Chair.java"; 
        // Replace with the path to your file 
        File file = new File(filePath); 
        assertTrue(file.exists() && file.isFile()); 
    }

	@Test 
    public void testrepositoryfolder() { 
        String directoryPath = "src/main/java/com/examly/springapp/repository"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
    @Test 
    public void testrepositoryFile() { 
        String filePath = "src/main/java/com/examly/springapp/repository/ChairRepository.java"; 
        // Replace with the path to your file 
        File file = new File(filePath); 
        assertTrue(file.exists() && file.isFile()); 
    }

	@Test 
    public void testServiceFolder() { 
        String directoryPath = "src/main/java/com/examly/springapp/service"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
    @Test 
    public void testServiceFile() { 
        String filePath = "src/main/java/com/examly/springapp/service/ChairService.java"; 
        // Replace with the path to your file 
        File file = new File(filePath); 
        assertTrue(file.exists() && file.isFile()); 
    }

  


    @Test
    public void testChairModelHasIdField() {
        checkFieldExists("com.examly.springapp.model.Chair", "chairId");
    }

    @Test
    public void testChairModelHasNameField() {
        checkFieldExists("com.examly.springapp.model.Chair", "name");
    }

    @Test
    public void testChairModelHasmaterialField() {
        checkFieldExists("com.examly.springapp.model.Chair", "material");
    }

     @Test
    public void testChairModelHascolorField() {
        checkFieldExists("com.examly.springapp.model.Chair", "color");
    }

    @Test
    public void testChairModelHasquantityField() {
        checkFieldExists("com.examly.springapp.model.Chair", "quantity");
    }

 @Test
public void testControllerHasRestControllerAnnotation() {
    checkAnnotationExists("com.examly.springapp.controller.ChairController", "org.springframework.web.bind.annotation.RestController");
}


   

    @Test
    public void testChairServiceAnnotation() {
        checkAnnotationExists("com.examly.springapp.service.ChairService", "org.springframework.stereotype.Service");
    }


   




    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

    private void checkClassImplementsInterface(String className, String interfaceName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> interfaceClazz = Class.forName(interfaceName);
            assertTrue(interfaceClazz.isAssignableFrom(clazz));
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " or interface " + interfaceName + " does not exist.");
        }
    }
	private void checkAnnotationExists(String className, String annotationName) {
		try {
			Class<?> clazz = Class.forName(className);
			ClassLoader classLoader = clazz.getClassLoader();
			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
		} catch (ClassNotFoundException | NullPointerException e) {
			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
		}
	}


@Test
	public void addChairs() throws Exception {
		String st = "{\"chairId\":5001,\"name\": \"demoChair\",\"material\": \"demo\",\"color\": \"blue\",\"quantity\": 101}";
		mockMvc.perform(MockMvcRequestBuilders.post("/chairs")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	

    @Test
    public void GetChairsByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chairs/5001")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
              //  .andExpect(jsonPath("$.name").value("demoChair"))
                .andReturn();
    }

    @Test
    public void getAllChairs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chairs")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$").isArray())
                .andReturn();
    }



	@Test
	public void updateChair() throws Exception{	

		String st =   "{\"chairId\":5001,\"name\": \"demoChair\",\"material\": \"demo\",\"color\": \"blue\",\"quantity\": 102}";
		  mockMvc.perform(MockMvcRequestBuilders.put("/chairs/5001")
		 				.contentType(MediaType.APPLICATION_JSON)
		 				.content(st)
		 				.accept(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk())
						//.andExpect(jsonPath("$.quantity").value(102))
						.andReturn();
	}

	@Test
public	void deleteChair() throws Exception{	

		 mockMvc.perform(MockMvcRequestBuilders.delete("/chairs/5001")
		 				.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").value(true))
						.andReturn();
	}



}
