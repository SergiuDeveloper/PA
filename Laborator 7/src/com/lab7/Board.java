package com.lab7;

public class Board {
    private Token[] tokens;
    public Token[] getTokens() {
        return this.tokens.clone();
    }
    private void setTokens(Token[] tokens) {
        this.tokens = tokens.clone();
    }

    public boolean assignTokenOwner(Player owner, int tokenIndex) {
        return this.tokens[tokenIndex].assignOwner(owner);
    }

    private int n;
    public int getN() {
        return this.n;
    }
    private void setN(int n) {
        this.n = n;
    }

    private int availableTokensCount;
    public int getAvailableTokensCount() {
        return this.availableTokensCount;
    }
    private void setAvailableTokensCount(int availableTokensCount) {
        this.availableTokensCount = availableTokensCount;
    }

    public Board(int n) {
        this.n = n;
        this.availableTokensCount = this.n;
        this.tokens = new Token[this.n + 1];
        for (int i = 1; i <= this.n; ++i)
            this.tokens[i] = new Token(i);
    }
}
