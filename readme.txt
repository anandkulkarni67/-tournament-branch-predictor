1. The input file is in the following format:
0n1
1t7
7n8
8t3
3n4
On each line:
• The first character is the address of the branch being executed.
• The second character is ‘n’ if the branch was not resolved to be taken or ‘t’ if the branch
was resolved to be taken. This field indicates the true (non-speculative) direction of the
branch.
• The third character is the address of the next instruction executed (the target if taken, the
fall-through address otherwise).

2. The output file is generated in the following format:
0nnlnn
1nnlnt
7nnlnn
8nnlnt
3nnlnn
On each line:
• The first character is the program address of the branch being predicted.
• The second character is the local prediction: ‘t’ if taken, ‘n’ if not taken.
• The third character is the global prediction: ‘t’ if taken, ‘n’ if not taken.
• The fourth character is the selector prediction: ‘l’ if local, ‘g’ if global.
• The fifth character is the final prediction: ‘t’ if taken, ‘n’ if not taken.
• The sixth character is the actual direction of the branch: ‘t’ if taken, ‘n’ if not taken.

Tournament predictor is comprised of 4 parts -
Part A is a local predictor: An array of 2-bit saturating counters, indexed by the instruction address. All counters initialize to zero. LocalPredictor.java implements part A.
Part B is a global predictor: An array of 2-bit saturating counters, indexed by the sequence of six resolved branch that occurred in time just prior to the branch being predicted. (This predictor considers only global history, not the PC of the branch.) All counters initialize to zero, and the running branch history initializes to six not-taken branches. GlobalPredictor.java implements Part B.
Part C is a selector: An array of 2-bit saturating counters, indexed by the instruction address, used to select between the local and global predictors when making a final prediction. All counters initialize to zero.
Part D is the tournament selection: Use the selector to choose between the local and global predictors to get the final prediction. TournamentPredictor.java implements Part C and Part D.

Updating saturating counters -
Based on the true outcome of each branch, updates to counters are as follows:
• For the local and global predictors:
o If the branch was taken, increment the saturating counter. If the branch was not
taken, decrement the counter.

• For the selector:
o If the local and global predictors agree (right or wrong), do not modify the counter.
o If the local predictor is correct and the global predictor is incorrect, decrement the
counter.
o If the local predictor is incorrect and the global predictor is correct, increment the
counter.