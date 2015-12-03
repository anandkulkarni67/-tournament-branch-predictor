SRCS = $(wildcard edu/mcs/beans/*.java edu/mcs/loader/*.java edu/mcs/predictors/*.java edu/mcs/driver/*.java)
CLS  = $(SRCS:.java=.class)

default:
	javac -classpath . $(SRCS)

clean:
	$(RM) $(CLS)
