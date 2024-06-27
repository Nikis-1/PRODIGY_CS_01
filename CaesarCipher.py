import sys

def encrypt(s,key):
        k=int(key)
        w="";
        for sh in s:
            if sh.isalpha():
                if sh.isupper():
                    mm = chr((ord(sh) - ord('A') + k) % 26 + ord('A'))
                else:
                    mm = chr((ord(sh) - ord('a') + k) % 26 + ord('a'))
            else:
                mm = sh
            w += mm
        return w

def decrypt(s,key):
        k=int(key)
        w=""; 
        for sh in s:
            if sh.isalpha():
                if sh.isupper():
                    mm = chr((ord(sh) - ord('A') - k + 26) % 26 + ord('A'))
                else:
                    mm = chr((ord(sh) - ord('a') - k + 26) % 26 + ord('a'))
            else:
                mm = sh
            w += mm
        return w

if __name__ == "__main__":
    if len(sys.argv) != 4:
        sys.exit(1)

    mode = sys.argv[1]
    text = sys.argv[2]
    key = sys.argv[3]

    if mode == "encrypt":
        print(encrypt(text, key))
    elif mode == "decrypt":
        print(decrypt(text, key))
    else:
        print("Invalid mode. Use 'encrypt' or 'decrypt'.")
        sys.exit(1)
