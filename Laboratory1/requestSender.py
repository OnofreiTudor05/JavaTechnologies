import urllib.request
import urllib.parse

try:
    x = urllib.request.urlopen('http://localhost:8080/Laboratory1/wordGen?word=elephant&size=0')
    print(x.read().decode('utf8'))
except Exception as e:
    print(str(e))
