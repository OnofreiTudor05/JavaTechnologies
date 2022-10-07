import urllib.request
import urllib.parse
import threading


def thread_function():
    try:
        x = urllib.request.urlopen('http://localhost:8080/Laboratory1/wordGen?word=elephant&size=0')
        word_list = x.read().decode('utf8')
        print(word_list)
    except Exception as e:
        print(str(e))


if __name__ == "__main__":
    for i in range(1, 20):
        new_thread = threading.Thread(target=thread_function())
        new_thread.start()
