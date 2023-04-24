package compiladores;

import java.io.BufferedReader;


public class Lexico {
    private String nomeArquivo;
    private BufferedReader br;
    private String caminhoArquivo;
    private int linha = 1;
    private int coluna = 1;


    public Lexico(String nomeArquivo) {
        
 
    }
 
    public Token getToken() {
        String caminhoArquivo = Paths.get(nomeArquivo).toAbsolutePath().toString();
        int c;
        char caractere;
 
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo, StandardCharsets.UTF_8))) {
 
            while (this.c != EOF) {
                if(Character.isWhitespace(this.c)) {
                  while(Character.isWhitespace(this.c) && this.c != EOF) {
                    if(this.c == '\n'){
                      this.linha++;
                      this.coluna = 1;
                    } else {
                      this.coluna++;
                    }
                    this.c = (char) br.read();
                  } 
                }
        
                else if(Character.isLetter(this.c)) {
                  while(Character.isLetter(this.c) || Character.isDigit(this.c)) {
                    lexema.append(this.c);
                    this.coluna++;
                    this.c = (char) br.read();
                  }
                  this.reservado = Arrays.asList(palRes).contains(lexema.toString().toLowerCase());
                  Token token = new Token();
                  if(this.reservado) {
                    token.setClasse(Classe.cPalRes);
                  } else {
                    token.setClasse(Classe.cIdent);
                  }
                  token.setValor(new Valor(lexema.toString()));
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.isDigit(this.c)) {
                  while(Character.isDigit(this.c)) {
                    lexema.append(this.c);
                    this.coluna++;
                    this.c = (char) br.read();
                  }
                  Token token = new Token();
                  token.setClasse(Classe.cInt);
                  token.setValor(new Valor(Integer.parseInt(lexema.toString())));
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.compare(this.c, '\'') == 0) {
                  this.c = (char) br.read();
                  while(Character.compare(this.c, '\'') != 0) {
                    lexema.append(this.c);
                    this.c = (char) br.read();
                    this.coluna++;
                  }
                  this.c = (char) br.read();
                  Token token = new Token();
                  token.setClasse(Classe.cString);
                  token.setValor(new Valor(lexema.toString()));
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.compare(this.c, '{') == 0) {
                  while(Character.compare(this.c, '}') != 0){
                    this.c = (char) br.read();
                    this.coluna++;
                  }
                  this.c = (char) br.read();
                }
        
                else if(Character.compare(this.c, '(') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cParEsq);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.compare(this.c, ')') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cParDir);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.compare(this.c, '+') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cSoma);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                } 
                
                else if(Character.compare(this.c, '-') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cSub);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                } 
                
                else if(Character.compare(this.c, '*') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cMult);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                } 
                
                else if(Character.compare(this.c, '/') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cDiv);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.compare(this.c, ':') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  if(Character.compare(this.c, '=') == 0) {
                    lexema.append(this.c);
                    this.c = (char) br.read();
                    this.coluna++;
                    Token token = new Token();
                    token.setClasse(Classe.cAtrib);
                    token.setLinha(this.linha);
                    token.setColuna(this.coluna - lexema.length());
                    return token;
                  } else {
                    Token token = new Token();
                    token.setClasse(Classe.cDoisPontos);
                    token.setLinha(this.linha);
                    token.setColuna(this.coluna - lexema.length());
                    return token;
                  }
                }
        
                else if(Character.compare(this.c, ';') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cPontoVirg);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.compare(this.c, '<') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  if(Character.compare(this.c, '=') == 0) {
                    lexema.append(this.c);
                    this.c = (char) br.read();
                    this.coluna++;
                    Token token = new Token();
                    token.setClasse(Classe.cMenorIgual);
                    token.setLinha(this.linha);
                    token.setColuna(this.coluna - lexema.length());
                    return token;
                  } else if(Character.compare(this.c, '>') == 0){
                    lexema.append(this.c);
                    this.c = (char) br.read();
                    this.coluna++;
                    Token token = new Token();
                    token.setClasse(Classe.cDif);
                    token.setLinha(this.linha);
                    token.setColuna(this.coluna - lexema.length());
                    return token;
                  } else {
                    Token token = new Token();
                    token.setClasse(Classe.cMenor);
                    token.setLinha(this.linha);
                    token.setColuna(this.coluna - lexema.length());
                    return token;
                  }
                }
        
                else if(Character.compare(this.c, '>') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  if(Character.compare(this.c, '=') == 0) {
                    lexema.append(this.c);
                    this.c = (char) br.read();
                    this.coluna++;
                    Token token = new Token();
                    token.setClasse(Classe.cMaiorIgual);
                    token.setLinha(this.linha);
                    token.setColuna(this.coluna - lexema.length());
                    return token;
                  } else {
                    Token token = new Token();
                    token.setClasse(Classe.cMaior);
                    token.setLinha(this.linha);
                    token.setColuna(this.coluna - lexema.length());
                    return token;
                  }
                }
        
                else if(Character.compare(this.c, '=') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cIgual);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.compare(this.c, '.') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cPonto);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
        
                else if(Character.compare(this.c, ',') == 0) {
                  lexema.append(this.c);
                  this.c = (char) br.read();
                  this.coluna++;
                  Token token = new Token();
                  token.setClasse(Classe.cVirgula);
                  token.setLinha(this.linha);
                  token.setColuna(this.coluna - lexema.length());
                  return token;
                }
              }
 
        } catch (IOException e) {
            System.err.println("Não foi possível abrir o arquivo ou ler do arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
    }
 
}