# Tematyka projektu: Przepisy kulinarne
### funkcjonalności
- tworzenie konta (wymagany istniejący email, na pocztę przychodzi mail z linkiem aktywacyjnym, jednak dla uproszczenia konto będzie od razu aktywne a istniejący mail jest wymagany aby przejść walidację)
- logowanie
- wyświetlenie wszystkich przepisów
- wyświetlenie swoich przepisów
- wyświetlenie szczegółów konkretnego przepisu
- dodawanie nowych przepisów
- edycja przepisów
- usuwanie przepisów

## Setup

```bash
minikube start
```
setup bazy
```bash
kubectl apply -f postgres-config.yaml
```

setup backendu
```bash
kubectl apply -f backend-config.yaml
```

setup frontendu
```bash
kubectl apply -f frontend-config.yaml
```


weryfikacja działania:
```bash
kubectl get pod
kubectl get deploy
kubectl get svc
kubectl get netpol
kubectl get pv,pvc
```


ze względu na wadę jaką jest system windows wymagane jest uruchomienie tunelowania aby dostać się na frontend 
```bash
minikube service frontend --url
```

### Dane domyślnego użytkownika
- login: bartekmark00@gmail.com
- hasło: test12345