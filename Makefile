# Des variables
SOURCE_DIR=src
BUILD_DIR=build
ANTLRPATH=grammaire/antlr.jar

CLASSES=$(patsubst $(SOURCE_DIR)/%.java, $(BUILD_DIR)/src/%.class, $(shell find $(SOURCE_DIR) -type f -name "*.java" -not -name "$(EXE).java" -not -path "$(SOURCE_DIR)/lp/*"))

EXE=Compilator
# Couleurs dans les echo
BLEU=\033[0;34m
GREEN=\033[0;32m
RED=\033[0;31m
YELLOW=\033[0;33m
CYAN=\033[0;36m
NC=\033[0m

# Recette par défaut
all: $(BUILD_DIR)/src/$(EXE).class

# Recette du Compilator
$(BUILD_DIR)/src/$(EXE).class: $(BUILD_DIR)/src/lp/whileLexer.class $(BUILD_DIR)/src/lp/whileParser.class $(CLASSES)
	@echo "$(BLEU)Compiling$(NC) $(GREEN)$(EXE)$(NC)"
	@javac -cp .:$(ANTLRPATH):$(BUILD_DIR) -d $(BUILD_DIR) $(SOURCE_DIR)/$(EXE).java

# Recette Lexer
$(BUILD_DIR)/src/lp/whileLexer.class: $(SOURCE_DIR)/lp/whileLexer.java 
	@echo "$(BLEU)Compiling$(NC) $(GREEN)whileLexer$(NC)"
	@javac -cp .:$(ANTLRPATH) -d $(BUILD_DIR) $(SOURCE_DIR)/lp/whileLexer.java 

# Recette Parser
$(BUILD_DIR)/src/lp/whileParser.class: $(SOURCE_DIR)/lp/whileParser.java 
	@echo "$(BLEU)Compiling$(NC) $(GREEN)whileParser$(NC)"
	@javac -cp .:$(ANTLRPATH) -d $(BUILD_DIR) $(SOURCE_DIR)/lp/whileParser.java 

# Génération du Lexer et du Parser
$(SOURCE_DIR)/lp/whileLexer.java: $(SOURCE_DIR)/lp/whileParser.java
$(SOURCE_DIR)/lp/whileParser.java: ./grammaire/while.g
	@echo "$(YELLOW)Compiling$(NC) $(GREEN)while.g$(NC)"
	@java -jar $(ANTLRPATH) -o src/lp grammaire/while.g
	@mv src/lp/grammaire/* src/lp/
	@rm -fr src/lp/grammaire

# Recette autres classes
$(BUILD_DIR)/src/%.class: $(SOURCE_DIR)/%.java
	@echo "$(BLEU)Compiling$(NC) $(GREEN)$*.class$(NC)"
	@javac -cp .:$(ANTLRPATH) -d $(BUILD_DIR) $<

# Éxecute le Compilator
start: $(BUILD_DIR)/src/$(EXE).class
	@echo "$(CYAN)Executing$(NC) $(GREEN)$(EXE)$(NC)"
	@java -cp .:$(ANTLRPATH):$(BUILD_DIR) src/$(EXE) "$(file)"

# Test du backEnd
testBackend:  backend/*.h 
	g++ backend/testBackend.cpp   backend/ArbreBinaire.cpp  -o test 


# Nettoie le projet
clean:
	@echo "$(RED)Cleaning$(NC) $(GREEN)project$(NC)"
	@rm -fr build
	@rm -fr src/lp
	@rm -fr grammaire/output
	@find . -name *.class -exec rm {} \;
