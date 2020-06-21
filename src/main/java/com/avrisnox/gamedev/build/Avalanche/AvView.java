package com.avrisnox.gamedev.build.Avalanche;

import com.avrisnox.gamedev.build.Avalanche.utils.FileAccess;
import com.avrisnox.gamedev.mvc.view.View;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

@SuppressWarnings({"WeakerAccess", "unused"})
public class AvView<MType extends AvModel> extends View<MType> {
	protected Shader shader;

	protected class Shader {
		int progId;
		int vertId;
		int fragId;

		public Shader() {
			progId = glCreateProgram();
		}

		public void attachVert(String name) {
			String src = FileAccess.readFromFile(name);
			vertId = glCreateShader(GL_VERTEX_SHADER);
			glShaderSource(vertId, src);
			glCompileShader(vertId);

			if(glGetShaderi(vertId, GL_COMPILE_STATUS) == GL_FALSE)
				throw new RuntimeException("Could not create vertex shader.");
			glAttachShader(progId, vertId);
		}

		public void attackFrag(String name) {
			String src = FileAccess.readFromFile(name);
			fragId = glCreateShader(GL_FRAGMENT_SHADER);
			glShaderSource(fragId, src);
			glCompileShader(fragId);

			if(glGetShaderi(fragId, GL_COMPILE_STATUS) == GL_FALSE)
				throw new RuntimeException("Could not create fragment shader.");
			glAttachShader(progId, fragId);
		}

		public void link() {
			glLinkProgram(progId);
			if(glGetProgrami(progId, GL_LINK_STATUS) == GL_FALSE)
				throw new RuntimeException("Could not link shader program.");
		}

		public void bind() {
			glUseProgram(progId);
		}

		public void unbind() {
			glUseProgram(0);
		}

		public void dispose() {
			unbind();
			glDetachShader(progId, vertId);
			glDeleteShader(vertId);
			glDetachShader(progId, fragId);
			glDeleteShader(fragId);
			glDeleteProgram(progId);
		}

		public int getId() {
			return progId;
		}
	}

	public AvView() {
		super();
	}

	@Override
	public void init(MType model) {
		super.init(model);
		GLFWErrorCallback.createPrint(System.err).set();

		if(!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		long window = glfwCreateWindow(640, 480, "Hello World!", NULL, NULL);
		if(window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		try(MemoryStack stacks = stackPush()) {
			IntBuffer pWidth = stacks.mallocInt(1);
			IntBuffer pHeight = stacks.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			if(vidmode == null)
				throw new RuntimeException("The video mode was null.");

			int xpos = (vidmode.width() - pWidth.get(0)) / 2;
			int ypos = (vidmode.height() - pHeight.get(0)) / 2;
			glfwSetWindowPos(window, xpos, ypos);
		}

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
		GL.createCapabilities();
		//glClearColor(0.3f, 0.3f, 0.3f, 0.0f);
		glClearColor(1.0f, 0.0f, 0.0f, 1.0f);

		this.model.setWindow(window);

		shader = new Shader();
		shader.attachVert("src/main/java/com/avrisnox/gamedev/build/Avalanche/shaders/vert.vs");
		shader.attackFrag("src/main/java/com/avrisnox/gamedev/build/Avalanche/shaders/frag.fs");
		shader.link();
	}

	@Override
	public void draw() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		shader.bind();
		model.getManager().POLY_MANAGER.renderPolys();
		shader.unbind();

		glfwSwapBuffers(model.getWindow());

	}

	@Override
	public void destroy() {
		shader.dispose();
	}
}
