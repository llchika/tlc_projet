start: compile_lp
	java -cp .:antlr.jar Main

compile_lp: compile_grammar
	javac -cp .:antlr.jar Main.java lp/*.java

compile_grammar:
	java -jar antlr.jar -o lp while.g

clean:
	rm -fr lp/
	rm -fr .antlr/
	rm Main.class