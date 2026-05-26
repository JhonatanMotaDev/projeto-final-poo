@echo off
echo ========================================
echo   Sistema de Delivery - Compilacao
echo ========================================
echo.

REM Criar diretorio bin se nao existir
if not exist "bin" mkdir bin

echo [1/3] Limpando compilacao anterior...
del /q bin\*.class 2>nul
for /d %%p in (bin\*) do rmdir "%%p" /s /q 2>nul

echo [2/3] Compilando o projeto...
javac -d bin -sourcepath src -encoding UTF-8 src\app\Main.java src\model\*.java src\view\*.java src\controller\*.java src\service\*.java src\interfaces\*.java src\enums\*.java

if %errorlevel% neq 0 (
    echo.
    echo [ERRO] Falha na compilacao!
    echo Verifique se o JDK esta instalado e configurado.
    pause
    exit /b 1
)

echo [3/3] Compilacao concluida com sucesso!
echo.
echo ========================================
echo   Iniciando o Sistema...
echo ========================================
echo.

REM Executar o programa
java -cp bin app.Main

if %errorlevel% neq 0 (
    echo.
    echo [ERRO] Falha ao executar o programa!
    pause
    exit /b 1
)

pause
